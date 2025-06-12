# Sistem Informasi Sekolah SMP Kartika XI-3 Jakarta

Ini adalah aplikasi Sistem Informasi Sekolah berbasis Java Swing yang dikembangkan menggunakan NetBeans IDE. Aplikasi ini dirancang untuk mengelola data siswa, absensi, nilai, laporan, dan rekomendasi, dengan backend database PostgreSQL dan fungsionalitas laporan menggunakan JasperReports.

## Fitur Utama

* **Login Multilevel:** Admin, Guru, dan Siswa.
* **Manajemen Data Siswa:** Admin dapat menambah, melihat, memperbarui, dan menghapus data siswa.
* **Manajemen Data Absensi:** Admin dapat menambah, melihat, memperbarui, dan menghapus data absensi siswa. Guru dapat melihat data absensi.
* **Manajemen Data Nilai:** Admin dapat menambah, melihat, memperbarui, dan menghapus data nilai siswa. Guru dapat melihat data nilai.
* **Laporan:** Menampilkan laporan absensi, nilai, dan rekomendasi menggunakan JasperReports.
* **Rekomendasi:** Menampilkan rekomendasi berdasarkan data siswa (nilai dan absensi) menggunakan logika Forward Chaining.
* **Akses Siswa:** Siswa dapat melihat data diri, absensi, nilai, dan rekomendasi mereka sendiri.

## Teknologi yang Digunakan

* **Bahasa Pemrograman:** Java
* **Platform GUI:** Java Swing
* **IDE:** Apache NetBeans IDE
* **Database:** PostgreSQL
* **JDBC Driver:** PostgreSQL JDBC Driver
* **Laporan:** JasperReports Library 7.0.3
* **Library Tambahan:** Apache Commons (BeanUtils, Collections, Digester, Logging)

## Struktur Proyek

* `src/`: Berisi semua kode sumber Java (`.java`) dan aset (`.jrxml`, `.jasper`, gambar ikon).
    * `src/sekolah/`: Kelas-kelas GUI (JFrame), kelas utama, dan kelas sesi.
    * `src/config/`: Kelas untuk konfigurasi koneksi database (`Koneksi.java`).
    * `src/gambar/`: Folder untuk menyimpan file gambar ikon yang digunakan di GUI.
    * `src/Report/`: Folder untuk menyimpan file laporan JasperReports (`.jrxml` dan `.jasper`).
* `lib/`: Berisi semua library eksternal (`.jar`) yang dibutuhkan oleh proyek. **Semua file JAR yang diperlukan sudah termasuk dalam repositori ini di folder `lib/`.**
* `nbproject/`: File konfigurasi proyek NetBeans.
* `build.xml`: Skrip Ant untuk proses build proyek.

## Cara Menjalankan Proyek

### Prasyarat

1.  **Java Development Kit (JDK):** Pastikan JDK 24 (atau versi kompatibel, seperti JDK 11/17) terinstal di sistem Anda.
2.  **PostgreSQL Database Server:** Instal dan jalankan PostgreSQL.
3.  **Database:** Buat database bernama `db_sekolah` di PostgreSQL.
4.  **Struktur Tabel Database:** Impor struktur tabel SQL dari file `[nama_file_struktur_database].sql` (jika tersedia di repositori) atau gunakan script DDL yang pernah diberikan.
5.  **Data Awal (Opsional, tapi Direkomendasikan):** Jalankan script SQL untuk mengisi data awal admin, guru, dan siswa yang tersedia (jika ada script-nya). Ini penting untuk pengujian login dan laporan.
6.  **Apache NetBeans IDE:** Instal NetBeans IDE.
7.  **Jaspersoft Studio Community Edition:** Instal Jaspersoft Studio (versi yang kompatibel, misalnya 7.0.3) direkomendasikan untuk mendesain dan mengompilasi ulang laporan JasperReports jika ada perubahan atau masalah kompatibilitas.

### Langkah-langkah Detail

1.  **Kloning Repositori:**
    ```bash
    git clone [https://github.com/MafyaCode/sekolah_v2.git](https://github.com/MafyaCode/sekolah_v2.git)
    cd sekolah_v2
    git checkout postgre # Pindah ke branch postgre
    ```
2.  **Verifikasi Library Eksternal:**
    * Pastikan folder `lib/` di proyek Anda (`C:\Users\[UserAnda]\Documents\sekolah\lib\`) berisi semua file JAR yang diperlukan. Daftar JAR yang dibutuhkan adalah:
        * `postgresql-42.7.7.jar` (atau versi terinstal)
        * `jasperreports-7.0.3.jar`
        * `jasperreports-fonts-7.0.3.jar`
        * `jasperreports-pdf-7.0.3.jar`
        * `commons-beanutils2-2.0.0-M2.jar`
        * `commons-collections4-4.5.0.jar`
        * `commons-digester3-3.2.jar`
        * `commons-logging-1.3.5.jar`
    * Jika ada masalah "it can't be read" saat build, pastikan semua file JAR ini **tidak terblokir oleh sistem operasi** (klik kanan > Properties > General > Unblock).
3.  **Siapkan Gambar (Icons):**
    * Pastikan semua file gambar ikon yang digunakan oleh aplikasi (`.jpg`, `.jpeg`, `.png`) berada di folder `src/gambar/` di proyek Anda.
    * Jika ada gambar yang baru dipindahkan/diunduh, lakukan "Unblock" pada properti file di Windows.
4.  **Konfigurasi dan Kompilasi Laporan JasperReports (Jika Ada Masalah Laporan):**
    * **Buka Jaspersoft Studio.**
    * Impor file `.jrxml` laporan Anda (`ReportLaporan.jrxml`, `ReportRekomendasi.jrxml`, dll.) ke dalam proyek di Jaspersoft Studio (misal: `MyReports/sekolah/`).
    * **Buat atau Konfigurasi Data Adapter PostgreSQL:**
        * Di Jaspersoft Studio, pergi ke `Window > Preferences > JasperReports > Data Adapter Definitions`.
        * Buat `New...` Data Adapter dengan tipe `Database JDBC Connection`.
        * Isi detail:
            * Name: `PostgreSQL_Sekolah_DB`
            * JDBC Driver: `PostgreSQL (org.postgresql.Driver)` (pastikan `postgresql-42.7.7.jar` atau yang relevan sudah ditambahkan ke driver Jaspersoft Studio jika belum ada).
            * JDBC URL: `jdbc:postgresql://localhost:5432/db_sekolah`
            * Username: `postgres`
            * Password: ``
            * Klik `Test Connection` untuk memverifikasi.
    * **Edit Query SQL di Setiap Laporan (.jrxml):**
        * Buka setiap file `.jrxml` di Jaspersoft Studio.
        * Pergi ke `Outline View > Dataset and Query > Query > Edit Query`.
        * Pilih `PostgreSQL_Sekolah_DB` sebagai Data Adapter.
        * **Koreksi SQL Query:** Hapus semua referensi `sekolah_smp.` dari nama tabel (misal `FROM anggota` alih-alih `FROM sekolah_smp.anggota`). Pastikan juga semua kolom yang dibutuhkan (`nama`, `nis`, `kelas`, `jumlahkehadiran`, `nilaiUH`, `nilaiUTS`, `nilaiUAS`, `nilaiAkhir`, `alamat`, `rekomendasi`) ada di `SELECT` statement. Gunakan alias (`a.`, `ab.`, `n.`, `r.`) dan `JOIN` yang benar.
        * Klik `Read Fields` untuk memverifikasi query.
    * **Kompilasi Laporan:**
        * Simpan file `.jrxml`.
        * Klik kanan pada file `.jrxml` di Project Explorer Jaspersoft Studio dan pilih `Compile Report`. Ini akan menghasilkan file `.jasper` yang kompatibel.
    * **Salin File `.jasper` ke Proyek NetBeans:**
        * Salin file `.jasper` yang baru terkompilasi dari folder proyek Jaspersoft Studio Anda (`[JaspersoftStudioWorkspace]/MyReports/sekolah/`) dan **timpa** file `.jasper` yang ada di `src/Report/` proyek NetBeans Anda.
5.  **Clean and Build Proyek di NetBeans:**
    * Tutup Apache NetBeans IDE sepenuhnya.
    * Hapus cache NetBeans Anda.
    * Buka kembali proyek di NetBeans.
    * Klik kanan pada proyek `sekolah`.
    * Pilih `Clean and Build`. Ini akan mengompilasi ulang proyek dan mengemasnya.
6.  **Jalankan Aplikasi:**
    * Klik kanan pada proyek `sekolah`.
    * Pilih `Run` (atau tekan `F6`).

## Informasi Login Default (Setelah mengisi data awal)

* **Admin:**
    * Username: `kepsek` / `ini kamu ea` / `staff`
    * Password: `kepsek123` / `kamu123` / `staff123`
* **Guru:**
    * Username: `gurumtk` / `gurubindo` / `guruinggris` / ...
    * Password: `guru123`
* **Siswa:**
    * Username: `siswaXA_001` / `siswaXB_001` / ...
    * Password: `pass1` (sesuai NIS, misal `siswaXA_001` passnya `pass1`)

## Kontribusi

Silakan hubungi pengembang jika ada pertanyaan atau untuk kontribusi lebih lanjut.