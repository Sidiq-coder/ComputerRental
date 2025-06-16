import java.time.LocalDateTime;
import java.time.Duration;
public class Komputer {
    private int idKomputer;
    private int nomorKomputer;
    private String status;
    private int durasiPenggunaan;
    private Pelanggan pelangganAktif;

    public Komputer(int idKomputer, int nomorKomputer) {
        this.idKomputer = idKomputer;
        this.nomorKomputer = nomorKomputer;
        this.status = "KOSONG"; // default saat dibuat
        this.durasiPenggunaan = 0;
        this.pelangganAktif = null;
    }
    

    public void aktifkan(Pelanggan pelanggan) {
        this.pelangganAktif = pelanggan;
        this.status = "AKTIF";
    }

    public void matikan() {
        this.pelangganAktif = null;
        this.status = "KOSONG";
        this.durasiPenggunaan = 0;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public int hitungDurasi(LocalDateTime mulai, LocalDateTime selesai) {
        int durasi = (int) Duration.between(mulai, selesai).toMinutes();
        this.durasiPenggunaan = durasi;
        return durasi;
    }

    // Getters and Setters
    public int getIdKomputer() { return idKomputer; }
    public void setIdKomputer(int idKomputer) { this.idKomputer = idKomputer; }
    public int getNomorKomputer() { return nomorKomputer; }
    public void setNomorKomputer(int nomorKomputer) { this.nomorKomputer = nomorKomputer; }
    public int getDurasiPenggunaan() { return durasiPenggunaan; }
    public void setDurasiPenggunaan(int durasiPenggunaan) { this.durasiPenggunaan = durasiPenggunaan; }
    public Pelanggan getPelangganAktif() { return pelangganAktif; }
    public void setPelangganAktif(Pelanggan pelangganAktif) { this.pelangganAktif = pelangganAktif; }
    public Pelanggan getPengguna() {return pelangganAktif;
}

}