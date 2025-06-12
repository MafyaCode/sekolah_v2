package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
    // Ganti driver ke PostgreSQL
    private static final String JDBC_DRIVER = "org.postgresql.Driver"; // Driver PostgreSQL
    // URL koneksi PostgreSQL. Port default 5432. Ganti db_sekolah_postgre dengan nama DB kamu
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/db_sekolah";
    // Ganti username dan password sesuai dengan user PostgreSQL kamu
    private static final String USER = "postgres"; // User default PostgreSQL
    private static final String PASS = "MafyaBrian25"; // Ganti dengan password PostgreSQL kamu

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Tidak perlu lagi Class.forName() di JDBC 4.0+ jika driver ada di classpath
            // tapi tidak ada salahnya jika tetap ditulis
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Koneksi ke PostgreSQL berhasil!");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver PostgreSQL tidak ditemukan: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Gagal koneksi ke PostgreSQL: " + e.getMessage());
            // Cetak SQLState dan ErrorCode untuk debugging lebih lanjut
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
        }
        return connection;
    }

    // Contoh main method untuk pengujian
    public static void main(String[] args) {
        Connection conn = Koneksi.getConnection();
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}