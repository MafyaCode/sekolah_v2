package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
    // Driver untuk PostgreSQL
    private static final String JDBC_DRIVER = "org.postgresql.Driver";

    // URL koneksi ke database PostgreSQL kamu.
    // Pastikan nama database 'db_sekolah' ini SAMA PERSIS dengan nama database di PostgreSQL kamu.
    // Port default PostgreSQL adalah 5432.
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/db_sekolah";

    // Username untuk koneksi ke database PostgreSQL kamu.
    // Default seringkali 'postgres', sesuaikan jika kamu menggunakan user lain.
    private static final String USER = "postgres";

    // Password untuk user database PostgreSQL kamu.
    // Ganti dengan password yang kamu set saat instalasi PostgreSQL atau saat membuat user.
    private static final String PASS = "MafyaBrian25"; // Sesuaikan dengan password kamu

    /**
     * Metode untuk mendapatkan objek Connection ke database PostgreSQL.
     * @return Objek Connection jika koneksi berhasil, null jika gagal.
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Memuat driver JDBC secara eksplisit (opsional untuk JDBC 4.0+, tapi tidak ada salahnya)
            Class.forName(JDBC_DRIVER);

            // Membuat koneksi ke database menggunakan URL, username, dan password
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Koneksi ke PostgreSQL berhasil!"); // Pesan sukses

        } catch (ClassNotFoundException e) {
            // Menangkap error jika driver JDBC tidak ditemukan
            System.err.println("Driver PostgreSQL tidak ditemukan: " + e.getMessage());
            e.printStackTrace(); // Cetak stack trace untuk detail error
        } catch (SQLException e) {
            // Menangkap error jika terjadi masalah saat koneksi ke database
            System.err.println("Gagal koneksi ke PostgreSQL: " + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState()); // Kode status SQL
            System.err.println("Error Code: " + e.getErrorCode()); // Kode error spesifik database
            e.printStackTrace(); // Cetak stack trace lengkap untuk debugging lebih lanjut
        }
        return connection;
    }

    /**
     * Metode main untuk menguji koneksi database secara langsung.
     * Kamu bisa menjalankan file ini (Shift + F6 di NetBeans) untuk memeriksa koneksi.
     * @param args Argumen command line (tidak digunakan).
     */
    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = Koneksi.getConnection(); // Coba mendapatkan koneksi
            if (conn != null) {
                System.out.println("Pengujian koneksi berhasil!");
            } else {
                System.out.println("Pengujian koneksi gagal: Objek koneksi null.");
            }
        } finally {
            // Pastikan koneksi ditutup meskipun terjadi error
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Koneksi ditutup.");
                } catch (SQLException e) {
                    System.err.println("Gagal menutup koneksi: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }
}