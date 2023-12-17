package mcd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {

    // JDBC URL, username, and password of MySQL server
    private static final String URL = "jdbc:mysql://localhost:3306/mcd";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // JDBC variables for opening, closing, and managing connection
    private static Connection connection;

    // Method to configure and return a database connection
    static Connection configDB() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    // Method to close the database connection
    static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    // Metode untuk menyimpan pesanan
    public static void savePesanan(int id_pelanggan, int totalHarga) {
        try {
            Connection conn = configDB();
            String sql = "INSERT INTO pesanan (id_pelanggan,total_harga) VALUES (?, ?)";
            try (PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setInt(1, id_pelanggan);
                pst.setInt(2, totalHarga);
                pst.executeUpdate();
            }
            closeConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Metode untuk mendapatkan semua pesanan
    public static void getAllPesanan() {
        try {
            Connection conn = configDB();
            String sql = "SELECT * FROM pesanan";
            try (PreparedStatement pst = conn.prepareStatement(sql);
                 ResultSet rs = pst.executeQuery()) {

                while (rs.next()) {
                    int idPesanan = rs.getInt("id_pesanan");
                    int totalHarga = rs.getInt("total_harga");
                    System.out.println("ID Pesanan: " + idPesanan + ", Total Harga: " + totalHarga);
                }
            }
            closeConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Metode untuk menyimpan data user
    public static void saveUser(String nama) {
        try {
            Connection conn = configDB();
            String sql = "INSERT INTO user (nama) VALUES ( ?)";
            try (PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(2,nama);
                pst.executeUpdate();
            }
            closeConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Metode untuk mendapatkan semua user
    public static void getAllUser() {
        try {
            Connection conn = configDB();
            String sql = "SELECT * FROM user";
            try (PreparedStatement pst = conn.prepareStatement(sql);
                 ResultSet rs = pst.executeQuery()) {

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nama = rs.getString("nama");
                    System.out.println("ID User: " + id + ", Nama: " + nama);
                }
            }
            closeConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Example of how to use the DatabaseManager class
        savePesanan(1, 100);
        getAllPesanan();

        saveUser("John Doe");
        getAllUser();
    }
}
