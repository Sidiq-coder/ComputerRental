import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Komputer> komputerList = new ArrayList<>();
    private static List<Pelanggan> pelangganList = new ArrayList<>();
    private static List<Operator> operatorList = new ArrayList<>();
    private static List<Transaksi> transaksiList = new ArrayList<>();

    public static void main(String[] args) {
        menuUtama();
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
            System.out.println("4. Logout");
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
                    System.out.println("Logout berhasil.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 4);
    }

    public static void prosesLoginPelanggan(Scanner scanner) {
        System.out.print("Masukkan ID Pelanggan: ");
        String id = scanner.nextLine();
        Pelanggan pelanggan = null;
        for (Pelanggan p : pelangganList) {
            if (p.getIdPelanggan().equals(id)) {
                pelanggan = p;
                break;
            }
        }
        if (pelanggan == null) {
            System.out.println("Pelanggan tidak ditemukan.");
            return;
        }

        Komputer komputerKosong = null;
        for (Komputer k : komputerList) {
            if (k.getStatus().equals("KOSONG")) {
                komputerKosong = k;
                break;
            }
        }

        if (komputerKosong == null) {
            System.out.println("Tidak ada komputer yang tersedia saat ini.");
            return;
        }

        komputerKosong.aktifkan(pelanggan);
        Transaksi transaksi = new Transaksi("TRX" + (transaksiList.size() + 1), pelanggan, komputerKosong, LocalDateTime.now());
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

} 

