/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sekolah;

/**
 * Kelas utilitas untuk menyimpan data sesi pengguna yang sedang login.
 */
public class UserSession {
    private static String username;
    private static int userId; // Untuk menyimpan ID pengguna (idanggota, idguru, idadmin)
    private static String userType; // Untuk menyimpan tipe pengguna (misal: "Siswa", "Guru", "Admin")

    public static void setUsername(String username) {
        UserSession.username = username;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUserId(int userId) {
        UserSession.userId = userId;
    }

    public static int getUserId() {
        return userId;
    }

    public static void setUserType(String userType) {
        UserSession.userType = userType;
    }

    public static String getUserType() {
        return userType;
    }

    // Metode untuk membersihkan sesi saat logout
    public static void clearSession() {
        UserSession.username = null;
        UserSession.userId = 0; // Set ID ke nilai default
        UserSession.userType = null;
    }
}