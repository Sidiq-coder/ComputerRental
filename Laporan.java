import java.time.LocalDate;
import java.util.List;

public class Laporan {
    private String idLaporan;
    private LocalDate periodeAwal;
    private LocalDate periodeAkhir;
    private List<Transaksi> daftarTransaksi;
    private double totalPendapatan;

    public void generateLaporan() {
        totalPendapatan = 0;
        for (Transaksi t : daftarTransaksi) {
            totalPendapatan += t.hitungTotalBayar();
        }
    }

    public void tampilkanRingkasan() {
        System.out.println("Laporan: " + idLaporan);
        System.out.println("Total Pendapatan: Rp" + totalPendapatan);
    }

    public void cetakLaporan() {
        tampilkanRingkasan();
        for (Transaksi t : daftarTransaksi) {
            t.cetakStruk();
        }
    }

    // Getters and Setters
    public String getIdLaporan() { return idLaporan; }
    public void setIdLaporan(String idLaporan) { this.idLaporan = idLaporan; }
    public LocalDate getPeriodeAwal() { return periodeAwal; }
    public void setPeriodeAwal(LocalDate periodeAwal) { this.periodeAwal = periodeAwal; }
    public LocalDate getPeriodeAkhir() { return periodeAkhir; }
    public void setPeriodeAkhir(LocalDate periodeAkhir) { this.periodeAkhir = periodeAkhir; }
    public List<Transaksi> getDaftarTransaksi() { return daftarTransaksi; }
    public void setDaftarTransaksi(List<Transaksi> daftarTransaksi) { this.daftarTransaksi = daftarTransaksi; }
    public double getTotalPendapatan() { return totalPendapatan; }
    public void setTotalPendapatan(double totalPendapatan) { this.totalPendapatan = totalPendapatan; }
    public String getIsiLaporan() {
    StringBuilder sb = new StringBuilder();
    for (Transaksi t : daftarTransaksi) {
        sb.append(t.toString()).append("\n");
    }
    return sb.toString();
}


}