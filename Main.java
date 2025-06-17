import java.time.LocalDateTime;
import java.time.LocalDate; 
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Komputer> komputerList = new ArrayList<>();
    private static List<Pelanggan> pelangganList = new ArrayList<>();
    private static List<Operator> operatorList = new ArrayList<>();
    private static List<Transaksi> transaksiList = new ArrayList<>();

    public static void main(String[] args) {
        operatorList.add(new Operator(1, "Admin", "admin", "admin123"));
        // Initialize some default data (optional)
        menuUtama();
    }
    
    public static void addAll(Operator o) {
        operatorList.add(o);
    }

    public static void menuUtama() {
        Scanner scanner = new Scanner(System.in);
        int pilihan;
        do {
            System.out.println("\n=== MENU UTAMA ==="); 
            System.out.println("1. Login sebagai Operator");
            System.out.println("2. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();
    
            switch (pilihan) {
                case 1:
                    prosesLoginOperator(scanner);
                    break;
                case 2:
                    System.out.println("Terima kasih telah menggunakan sistem warnet.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 2);
    }


    public static void tambahDataMenu(Scanner scanner) {
        System.out.println("\n=== TAMBAH DATA ===");
        System.out.println("1. Tambah Operator");
        System.out.println("2. Tambah Komputer");
        System.out.println("3. Tambah Pelanggan");
        System.out.println("4. Kembali ke Menu Utama");
        System.out.print("Pilih menu: ");
        int pilihan = scanner.nextInt();
        scanner.nextLine();

        switch (pilihan) {
            case 1:
                tambahOperator(scanner);
                break;
            case 2:
                tambahKomputer(scanner);
                break;
            case 3:
                tambahPelanggan(scanner);
                break;
            case 4:
                break;
            default:
                System.out.println("Pilihan tidak valid.");
        }
    }

    private static void tambahOperator(Scanner scanner) {
        System.out.println("\nTambah Operator Baru");
        System.out.print("ID Operator: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Username: ");
        String username = scanner.nextLine();
        
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        System.out.print("Nama: ");
        String nama = scanner.nextLine();
        
        operatorList.add(new Operator(id, nama, username, password ));
        System.out.println("Operator berhasil ditambahkan.");
    }

    private static void tambahKomputer(Scanner scanner) {
        System.out.println("\nTambah Komputer Baru");
        System.out.print("Nomor Komputer: ");
        int nomor = scanner.nextInt();
        scanner.nextLine();
        
        komputerList.add(new Komputer(komputerList.size() + 1, nomor));
        System.out.println("Komputer berhasil ditambahkan dengan Nomor: " + nomor);
    }

    private static void tambahPelanggan(Scanner scanner) {
        System.out.println("\nTambah Pelanggan Baru");
        System.out.print("ID Pelanggan: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Nama: ");
        String nama = scanner.nextLine();
        
        System.out.print("No. HP: ");
        String noHp = scanner.nextLine();
        
        pelangganList.add(new Pelanggan(id, nama, noHp));
        System.out.println("Pelanggan berhasil ditambahkan.");
    }

    public static void addAll(Operator o, Pelanggan p, Komputer k) {
        operatorList.add(o);
        pelangganList.add(p);
        komputerList.add(k);
    }
    
    public static List<Operator> getOperatorList() {
        return operatorList;
    }
   

    public static void prosesLoginOperator(Scanner scanner) {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        for (Operator o : operatorList) {
            if (o.login(username, password)) {
                System.out.println("Login berhasil. Selamat datang, " + o.getNama());
                mulaiSesi(scanner, o);
                return;
            }
        }
        System.out.println("Login gagal. Username atau password salah.");
    }

    public static void mulaiSesi(Scanner scanner, Operator operator) {
        int pilihan;
        do {
            System.out.println("\n=== MENU OPERATOR ===");
            System.out.println("1. Monitor Komputer");
            System.out.println("2. Mulai Sesi Penggunaan");
            System.out.println("3. Akhiri Sesi Penggunaan");
            System.out.println("4. Tambah Data (Operator/Komputer/Pelanggan)");
            System.out.println("5. Logout");
            System.out.println("6. Cetak Laporan"); 
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    operator.monitorKomputer(komputerList);
                    break;
                case 2:
                    prosesLoginPelanggan(scanner);
                    break;
                case 3:
                    akhiriSesi(scanner);
                    break;
                case 4:
                    tambahDataMenu(scanner);
                    break;
                case 5:
                    System.out.println("Logout berhasil.");
                    break;
                case 6:
                    cetakLaporan();
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 5);
    }

    public static void prosesLoginPelanggan(Scanner scanner) {
        System.out.print("Masukkan ID Pelanggan: ");
        int id = scanner.nextInt(); 
        scanner.nextLine(); 

        Pelanggan pelanggan = null;
        for (Pelanggan p : pelangganList) {
            if (p.getIdPelanggan() == id) { 
                pelanggan = p;
                break;
            }
        }
        if (pelanggan == null) {
            System.out.println("Pelanggan tidak ditemukan.");
            return;
        }

        // Tampilkan daftar komputer kosong
        System.out.println("Daftar Komputer Tersedia:");
        boolean adaKosong = false;
        for (Komputer k : komputerList) {
            if (k.getStatus().equals("KOSONG")) {
                System.out.println("- Nomor Komputer: " + k.getNomorKomputer());
                adaKosong = true;
            }
        }
        if (!adaKosong) {
            System.out.println("Tidak ada komputer yang tersedia saat ini.");
            return;
        }

        System.out.print("Masukkan nomor komputer yang ingin digunakan: ");
        int nomorKomputer = scanner.nextInt();
        scanner.nextLine();

        Komputer komputerKosong = null;
        for (Komputer k : komputerList) {
            if (k.getNomorKomputer() == nomorKomputer && k.getStatus().equals("KOSONG")) {
                komputerKosong = k;
                break;
            }
        }

        if (komputerKosong == null) {
            System.out.println("Komputer tidak tersedia atau nomor salah.");
            return;
        }

        komputerKosong.aktifkan(pelanggan);
        Transaksi transaksi = new Transaksi(transaksiList.size() + 1, pelanggan, komputerKosong, LocalDateTime.now());        
        transaksiList.add(transaksi);
        System.out.println("Sesi dimulai di Komputer " + komputerKosong.getNomorKomputer());
    }

    public static void akhiriSesi(Scanner scanner) {
        System.out.print("Masukkan ID Komputer: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Komputer komputer = null;
        for (Komputer k : komputerList) {
            if (k.getNomorKomputer() == id) {
                komputer = k;
                break;
            }
        }

        if (komputer == null || komputer.getStatus().equals("KOSONG")) {
            System.out.println("Komputer tidak dalam sesi aktif.");
            return;
        }

        for (Transaksi t : transaksiList) {
            if (t.getKomputer().equals(komputer) && t.getWaktuSelesai() == null) {
                t.setWaktuSelesai(LocalDateTime.now());
                t.hitungDurasi();
                t.hitungTotalBayar();
                komputer.matikan();
                t.cetakStruk();
                break;
            }
        }
    }
    
    public static void cetakLaporan() {
        Laporan laporan = new Laporan();
        laporan.setIdLaporan("LAP" + System.currentTimeMillis());
        laporan.setPeriodeAwal(LocalDate.now().minusDays(7)); 
        laporan.setPeriodeAkhir(LocalDate.now());
        laporan.setDaftarTransaksi(transaksiList); 
        laporan.generateLaporan();
        laporan.cetakLaporan();
    } 
        
    public static int generateId() {return (int) System.currentTimeMillis();}
    public static List<Komputer> getKomputerList() {return komputerList;}
    public static List<Transaksi> getTransaksiList() {return transaksiList;}
    public static List<Pelanggan> getPelangganList() {return pelangganList;}

}