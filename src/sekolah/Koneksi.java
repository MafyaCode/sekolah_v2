/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sekolah;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

/**
 *
 * @author fitri
 */
public class Koneksi {
private static Connection koneksi;
    public static Connection getKoneksi() {
        if (koneksi == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/sekolah_smp", "root", "");
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Error: "+ e.getMessage());
            }
        }
        return koneksi;
    }
    public static void main(String[] args) {
        Connection koneksi = getKoneksi();
        if (koneksi !=null) {
            System.out.println("Koneksi berhasil!");
        } else {
            System.out.println("Koneksi gagal!");
        }
    }
}