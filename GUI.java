import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GUI extends JFrame {
    private JPanel panelLogin, panelMenu;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JTextArea textAreaOutput;
    private JTable tableKomputer;
    private Operator currentOperator;

    public GUI() {
        setTitle("Sistem Warnet - Login");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initLoginPanel();
        setVisible(true);
    }

    private void initLoginPanel() {
        panelLogin = new JPanel(new GridLayout(4, 2, 10, 10));
        panelLogin.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        panelLogin.add(new JLabel("Username:"));
        txtUsername = new JTextField();
        panelLogin.add(txtUsername);

        panelLogin.add(new JLabel("Password:"));
        txtPassword = new JPasswordField();
        panelLogin.add(txtPassword);

        JButton btnLogin = new JButton("Login");
        panelLogin.add(btnLogin);

        JButton btnExit = new JButton("Keluar");
        panelLogin.add(btnExit);

        add(panelLogin);

        btnLogin.addActionListener(e -> prosesLogin());
        btnExit.addActionListener(e -> System.exit(0));
    }

    private void prosesLogin() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        for (Operator o : Main.getOperatorList()) {
            if (o.login(username, password)) {
                currentOperator = o;
                JOptionPane.showMessageDialog(this, "Login berhasil! Selamat datang, " + o.getNama());
                showMenuPanel();
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Login gagal. Username atau password salah.");
    }

    private void showMenuPanel() {
        getContentPane().removeAll();
        setTitle("Sistem Warnet - Menu Operator");

        panelMenu = new JPanel();
        panelMenu.setLayout(new BorderLayout());

        JPanel panelButtons = new JPanel(new GridLayout(3, 3, 10, 10));
        panelButtons.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnMonitorKomputer = new JButton("Monitor Komputer");
        JButton btnCetakLaporan = new JButton("Cetak Laporan");
        JButton btnLogout = new JButton("Logout");
        JButton btnMulaiSesi = new JButton("Mulai Sesi");
        JButton btnAkhiriSesi = new JButton("Akhiri Sesi");
        JButton btnTambahData = new JButton("Tambah Data");

        panelButtons.add(btnMonitorKomputer);
        panelButtons.add(btnMulaiSesi);
        panelButtons.add(btnAkhiriSesi);
        panelButtons.add(btnTambahData);
        panelButtons.add(btnCetakLaporan);
        panelButtons.add(btnLogout);
        
        textAreaOutput = new JTextArea(10, 30);
        textAreaOutput.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textAreaOutput);

        panelMenu.add(panelButtons, BorderLayout.NORTH);
        panelMenu.add(scrollPane, BorderLayout.CENTER);

        add(panelMenu);
        revalidate();
        repaint();

        btnMonitorKomputer.addActionListener(e -> tampilkanDataKomputer());
        btnCetakLaporan.addActionListener(e -> cetakLaporan());
        btnLogout.addActionListener(e -> logout());
        btnMulaiSesi.addActionListener(e -> mulaiSesi());
        btnAkhiriSesi.addActionListener(e -> akhiriSesi());
        btnTambahData.addActionListener(e -> tambahData());
    }
    
    
    private void mulaiSesi() {
        List<Komputer> komputerList = Main.getKomputerList();
        List<Pelanggan> pelangganList = Main.getPelangganList();

        if (komputerList.isEmpty() || pelangganList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Data komputer atau pelanggan kosong!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] pelangganOptions = pelangganList.stream().map(Pelanggan::getNama).toArray(String[]::new);
        String namaPelanggan = (String) JOptionPane.showInputDialog(this, "Pilih Pelanggan:", "Mulai Sesi", JOptionPane.QUESTION_MESSAGE, null, pelangganOptions, pelangganOptions[0]);
    
        Pelanggan selectedPelanggan = pelangganList.stream().filter(p -> p.getNama().equals(namaPelanggan)).findFirst().orElse(null);
    
        String nomorKomputer = JOptionPane.showInputDialog(this, "Masukkan Nomor Komputer:");

        for (Komputer k : komputerList) {
            if (String.valueOf(k.getNomorKomputer()).equals(nomorKomputer) && k.getStatus().equals("Tersedia")) {
                Transaksi t = currentOperator.prosesTransaksi(selectedPelanggan, k);
                Main.getTransaksiList().add(t);
                JOptionPane.showMessageDialog(this, "Sesi dimulai untuk Komputer " + nomorKomputer);
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Komputer tidak tersedia atau nomor salah.");
        }

    private void akhiriSesi() {
        List<Transaksi> transaksiList = Main.getTransaksiList();

        if (transaksiList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Belum ada transaksi.");
            return;
        }

        String[] transaksiOptions = transaksiList.stream().map(t -> "ID " + t.getIdTransaksi() + " - " + t.getPelanggan().getNama()).toArray(String[]::new);
        String pilihan = (String) JOptionPane.showInputDialog(this, "Pilih Transaksi:", "Akhiri Sesi", JOptionPane.QUESTION_MESSAGE, null, transaksiOptions, transaksiOptions[0]);

        Transaksi t = transaksiList.stream().filter(tx -> pilihan.contains(String.valueOf(tx.getIdTransaksi()))).findFirst().orElse(null);

        if (t != null) {
            int durasi = t.hitungDurasi();
            double bayar = t.hitungTotalBayar();
            t.getKomputer().matikan();
            JOptionPane.showMessageDialog(this, "Durasi: " + durasi + " menit\nTotal Bayar: Rp" + bayar);
        }
    }
    
    private void tambahData() {
        String[] opsi = {"Tambah Operator", "Tambah Komputer", "Tambah Pelanggan"};
        String pilihan = (String) JOptionPane.showInputDialog(this, "Pilih Data yang Ingin Ditambah:", "Tambah Data", JOptionPane.QUESTION_MESSAGE, null, opsi, opsi[0]);

        switch (pilihan) {
            case "Tambah Operator":
                String namaOp = JOptionPane.showInputDialog("Nama:");
                String userOp = JOptionPane.showInputDialog("Username:");
                String passOp = JOptionPane.showInputDialog("Password:");
                Main.getOperatorList().add(new Operator(Main.generateId(), namaOp, userOp, passOp));
                break;
            case "Tambah Komputer":
                int nomor = Integer.parseInt(JOptionPane.showInputDialog("Nomor Komputer:"));
                Main.getKomputerList().add(new Komputer(Main.generateId(), nomor));
                break;
            case "Tambah Pelanggan":
                String namaP = JOptionPane.showInputDialog("Nama Pelanggan:");
                String telp = JOptionPane.showInputDialog("No Telepon:");
                Main.getPelangganList().add(new Pelanggan(Main.generateId(), namaP, telp));
                break;
        }

        JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan.");
    }



    private void tampilkanDataKomputer() {
        List<Komputer> list = Main.getKomputerList();

        String[] columnNames = {"ID", "Nomor Komputer", "Status", "Nama Pengguna"};
        String[][] data = new String[list.size()][4];

        for (int i = 0; i < list.size(); i++) {
            Komputer k = list.get(i);
            data[i][0] = String.valueOf(k.getIdKomputer());
            data[i][1] = String.valueOf(k.getNomorKomputer());
            data[i][2] = k.getStatus();
            data[i][3] = k.getPengguna() != null ? k.getPengguna().getNama() : "-";
        }

        tableKomputer = new JTable(new DefaultTableModel(data, columnNames));
        JOptionPane.showMessageDialog(this, new JScrollPane(tableKomputer), "Data Komputer", JOptionPane.INFORMATION_MESSAGE);
    }

    private void cetakLaporan() {
        Laporan laporan = new Laporan();
        laporan.setIdLaporan("LAP" + System.currentTimeMillis());
        laporan.setPeriodeAwal(java.time.LocalDate.now().minusDays(7));
        laporan.setPeriodeAkhir(java.time.LocalDate.now());
        laporan.setDaftarTransaksi(Main.getTransaksiList());
        laporan.generateLaporan();
    
        // Ubah daftar transaksi menjadi String
        String isiLaporan = laporan.getIsiLaporan();
    
        JTextArea laporanArea = new JTextArea(isiLaporan);
        laporanArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(laporanArea);
        scrollPane.setPreferredSize(new Dimension(600, 400));
    
        JOptionPane.showMessageDialog(this, scrollPane, "Laporan Mingguan", JOptionPane.INFORMATION_MESSAGE);
    }

    private void logout() {
        currentOperator = null;
        getContentPane().removeAll();
        initLoginPanel();
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        // Menambahkan akun default agar bisa login
        Main.addAll(new Operator(1, "Admin", "admin", "admin123"));
        SwingUtilities.invokeLater(GUI::new);
    }
}