import java.time.LocalDate;

public class Member {
    private String idMember;
    private Pelanggan pelanggan;
    private LocalDate tanggalDaftar;
    private LocalDate tanggalKadaluarsa;
    private double saldo;

    public boolean cekAktif() {
        return LocalDate.now().isBefore(tanggalKadaluarsa);
    }

    public void isiSaldo(double nominal) {
        this.saldo += nominal;
    }

    public void potongSaldo(double nominal) {
        if (saldo >= nominal) saldo -= nominal;
        else System.out.println("Saldo tidak cukup.");
    }

    // Getters and Setters
    public String getIdMember() { return idMember; }
    public void setIdMember(String idMember) { this.idMember = idMember; }
    public Pelanggan getPelanggan() { return pelanggan; }
    public void setPelanggan(Pelanggan pelanggan) { this.pelanggan = pelanggan; }
    public LocalDate getTanggalDaftar() { return tanggalDaftar; }
    public void setTanggalDaftar(LocalDate tanggalDaftar) { this.tanggalDaftar = tanggalDaftar; }
    public LocalDate getTanggalKadaluarsa() { return tanggalKadaluarsa; }
    public void setTanggalKadaluarsa(LocalDate tanggalKadaluarsa) { this.tanggalKadaluarsa = tanggalKadaluarsa; }
    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }
}