public class Pelanggan {
    private int idPelanggan;
    private String nama;
    private String noTelepon; 

    public Pelanggan(int idPelanggan, String nama, String noTelepon){
        this.idPelanggan = idPelanggan;
        this.nama = nama;
        this.noTelepon = noTelepon;
    }
    
    public void login() {
        System.out.println(nama + " telah login.");
    }

    public void logout() {
        System.out.println(nama + " telah logout.");
    }

    public void tampilInfo() {
        System.out.println("ID: " + idPelanggan + ", Nama: " + nama + ", Tipe Akun: " );
    }

    // Getters and Setters
    public int getIdPelanggan() { return idPelanggan; }
    public void setIdPelanggan(int idPelanggan) { this.idPelanggan = idPelanggan; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public String getNoTelepon() { return noTelepon; }
    public void setNoTelepon(String noTelepon) { this.noTelepon = noTelepon; }
     

    
}