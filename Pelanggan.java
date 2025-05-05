public class Pelanggan {
    private String idPelanggan;
    private String nama;
    private String tipeAkun;
    private String noTelepon;

    public void login() {
        System.out.println(nama + " telah login.");
    }

    public void logout() {
        System.out.println(nama + " telah logout.");
    }

    public void tampilInfo() {
        System.out.println("ID: " + idPelanggan + ", Nama: " + nama + ", Tipe Akun: " + tipeAkun);
    }

    // Getters and Setters
    public String getIdPelanggan() { return idPelanggan; }
    public void setIdPelanggan(String idPelanggan) { this.idPelanggan = idPelanggan; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public String getTipeAkun() { return tipeAkun; }
    public void setTipeAkun(String tipeAkun) { this.tipeAkun = tipeAkun; }
    public String getNoTelepon() { return noTelepon; }
    public void setNoTelepon(String noTelepon) { this.noTelepon = noTelepon; }
}