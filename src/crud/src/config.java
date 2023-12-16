
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Lenovo
 */
public class config {
    private static Connection mysqlconfig;

    public static Connection configDB() throws SQLException {
        try {
            String url = "http://localhost/phpmyadmin/index.php?route=/database/export&db=order_mcd";
            String id = "root";
            String nama = "";
            mysqlconfig = DriverManager.getConnection(url, id, nama);
        } catch (SQLException e) {
            throw new SQLException("Koneksi gagal: " + e.getMessage());
        }
        return mysqlconfig;
    }

    // Metode untuk menutup koneksi
    public static void closeConnection() {
        try {
            if (mysqlconfig != null && !mysqlconfig.isClosed()) {
                mysqlconfig.close();
            }
        } catch (SQLException e) {
            System.err.println("Gagal menutup koneksi: " + e.getMessage());
        }
    }
}
