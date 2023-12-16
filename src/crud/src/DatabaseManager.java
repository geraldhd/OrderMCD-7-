import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {

    // Metode untuk menyimpan pesanan
    public static void savePesanan(int idPesanan, int totalHarga) {
        try {
            Connection conn = config.configDB();
            String sql = "INSERT INTO pesanan (id_pesanan, total_harga) VALUES (?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, idPesanan);
            pst.setInt(2, totalHarga);
            pst.executeUpdate();
            pst.close();
            config.closeConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Metode untuk mendapatkan semua pesanan
    public static void getAllPesanan() {
        try {
            Connection conn = config.configDB();
            String sql = "SELECT * FROM pesanan";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int idPesanan = rs.getInt("id_pesanan");
                int totalHarga = rs.getInt("total_harga");
                System.out.println("ID Pesanan: " + idPesanan + ", Total Harga: " + totalHarga);
            }

            rs.close();
            pst.close();
            config.closeConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Metode untuk menyimpan data user
    public static void saveUser(int id, String nama) {
        try {
            Connection conn = config.configDB();
            String sql = "INSERT INTO user (id, nama) VALUES (?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.setString(2, nama);
            pst.executeUpdate();
            pst.close();
            config.closeConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Metode untuk mendapatkan semua user
    public static void getAllUser() {
        try {
            Connection conn = config.configDB();
            String sql = "SELECT * FROM user";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nama = rs.getString("nama");
                System.out.println("ID User: " + id + ", Nama: " + nama);
            }

            rs.close();
            pst.close();
            config.closeConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
