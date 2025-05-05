import java.util.List;
import java.time.LocalDateTime;
public class Operator {
    private int idOperator;
    private String nama;
    private String username;
    private String password;

    public Operator(int idOperator, String nama, String username, String password) {
        this.idOperator = idOperator;
        this.nama = nama;
        this.username = username;
        this.password = password;
    }

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public void monitorKomputer(List<Komputer> komputerList) {
        for (Komputer k : komputerList) {
            System.out.println("Komputer: " + k.getNomorKomputer() + " - Status: " + k.getStatus());
        }
    }

    public Transaksi prosesTransaksi(Pelanggan p, Komputer k) {
        LocalDateTime mulai = LocalDateTime.now();
        k.aktifkan(p);
        return new Transaksi((int) System.currentTimeMillis(), p, k, mulai);
    }

    // Getters and Setters
    public int getIdOperator() { return idOperator; }
    public void setIdOperator(int idOperator) { this.idOperator = idOperator; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}