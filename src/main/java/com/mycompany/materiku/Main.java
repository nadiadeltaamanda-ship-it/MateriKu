/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.materiku;

/**
 *
 * @author ASUS
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final Scanner input = new Scanner(System.in);
    private static final ArsipMateri arsip = new ArsipMateri();

    public static void main(String[] args) {
        boolean jalan = true;

        System.out.println("=====================================");
        System.out.println("   MateriKu - Arsip Materi Kuliah    ");
        System.out.println("=====================================");

        while (jalan) {
            tampilkanMenu();
            int pilihan = bacaAngka("Pilih menu: ");

            switch (pilihan) {
                case 1:
                    tampilkanDashboard();
                    break;
                case 2:
                    menuMataKuliah();
                    break;
                case 3:
                    tambahMateri();
                    break;
                case 4:
                    cariMateri();
                    break;
                case 5:
                    filterMateri();
                    break;
                case 6:
                    lihatDetailMateri();
                    break;
                case 7:
                    ubahMateri();
                    break;
                case 8:
                    hapusMateri();
                    break;
                case 9:
                    tambahCatatan();
                    break;
                case 0:
                    jalan = false;
                    System.out.println("Terima kasih telah menggunakan MateriKu!");
                    break;
                default:
                    System.out.println("Menu tidak tersedia, coba lagi.");
            }
        }
        input.close();
    }

    // ---------------- Tampilan menu ----------------
    private static void tampilkanMenu() {
        System.out.println("\n========== MENU UTAMA ==========");
        System.out.println("1. Dashboard");
        System.out.println("2. Kelola Mata Kuliah");
        System.out.println("3. Tambah Materi");
        System.out.println("4. Cari Materi");
        System.out.println("5. Filter Materi");
        System.out.println("6. Lihat Detail Materi");
        System.out.println("7. Ubah Materi");
        System.out.println("8. Hapus Materi");
        System.out.println("9. Tambah Catatan Pribadi");
        System.out.println("0. Keluar");
        System.out.println("================================");
    }

    // ---------------- Helper input Scanner ----------------
    private static String bacaTeks(String prompt) {
        System.out.print(prompt);
        return input.nextLine().trim();
    }

    private static String bacaTeksWajib(String prompt) {
        String teks;
        do {
            teks = bacaTeks(prompt);
            if (teks.isEmpty()) {
                System.out.println("Input tidak boleh kosong.");
            }
        } while (teks.isEmpty());
        return teks;
    }

    private static int bacaAngka(String prompt) {
        while (true) {
            String teks = bacaTeks(prompt);
            try {
                return Integer.parseInt(teks);
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka.");
            }
        }
    }

    // ---------------- 1. Dashboard ----------------
    private static void tampilkanDashboard() {
        System.out.println("\n----------- DASHBOARD -----------");
        System.out.println("Jumlah mata kuliah   : " + arsip.getDaftarMataKuliah().size());
        System.out.println("Jumlah materi        : " + arsip.getDaftarMateri().size());
        System.out.println("Materi terakhir ditambahkan:");

        ArrayList<Materi> semua = arsip.getDaftarMateri();
        if (semua.isEmpty()) {
            System.out.println("   (belum ada materi)");
        } else {
            int mulai = Math.max(0, semua.size() - 3);
            for (int i = semua.size() - 1; i >= mulai; i--) {
                System.out.print("   ");
                semua.get(i).tampilkanBaris();
            }
        }
    }

    // ---------------- 2. Kelola Mata Kuliah ----------------
    private static void menuMataKuliah() {
        boolean kembali = false;
        while (!kembali) {
            System.out.println("\n--- Kelola Mata Kuliah ---");
            System.out.println("1. Tambah mata kuliah");
            System.out.println("2. Lihat daftar mata kuliah");
            System.out.println("3. Ubah mata kuliah");
            System.out.println("4. Hapus mata kuliah");
            System.out.println("0. Kembali");
            int pilih = bacaAngka("Pilih: ");

            switch (pilih) {
                case 1: {
                    String kode = bacaTeksWajib("Kode mata kuliah : ");
                    String nama = bacaTeksWajib("Nama mata kuliah : ");
                    if (arsip.tambahMataKuliah(new MataKuliah(kode, nama))) {
                        System.out.println("Mata kuliah berhasil ditambahkan.");
                    } else {
                        System.out.println("Kode mata kuliah sudah digunakan.");
                    }
                    break;
                }
                case 2:
                    tampilkanDaftarMataKuliah();
                    break;
                case 3: {
                    String kode = bacaTeksWajib("Kode mata kuliah yang diubah: ");
                    MataKuliah mk = arsip.cariMataKuliah(kode);
                    if (mk == null) {
                        System.out.println("Mata kuliah tidak ditemukan.");
                    } else {
                        String namaBaru = bacaTeks("Nama baru (kosongkan jika tidak diubah): ");
                        if (!namaBaru.isEmpty()) {
                            mk.setNama(namaBaru);
                            System.out.println("Mata kuliah berhasil diubah.");
                        }
                    }
                    break;
                }
                case 4: {
                    String kode = bacaTeksWajib("Kode mata kuliah yang dihapus: ");
                    String yakin = bacaTeks("Semua materi pada mata kuliah ini ikut terhapus. Lanjut? (y/n): ");
                    if (yakin.equalsIgnoreCase("y")) {
                        if (arsip.hapusMataKuliah(kode)) {
                            System.out.println("Mata kuliah berhasil dihapus.");
                        } else {
                            System.out.println("Mata kuliah tidak ditemukan.");
                        }
                    } else {
                        System.out.println("Penghapusan dibatalkan.");
                    }
                    break;
                }
                case 0:
                    kembali = true;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private static boolean tampilkanDaftarMataKuliah() {
        ArrayList<MataKuliah> daftar = arsip.getDaftarMataKuliah();
        if (daftar.isEmpty()) {
            System.out.println("Belum ada mata kuliah.");
            return false;
        }
        System.out.println("Daftar Mata Kuliah:");
        for (int i = 0; i < daftar.size(); i++) {
            System.out.println((i + 1) + ". " + daftar.get(i));
        }
        return true;
    }

    // ---------------- 3. Tambah Materi ----------------
    private static void tambahMateri() {
        if (!tampilkanDaftarMataKuliah()) {
            System.out.println("Tambahkan mata kuliah terlebih dahulu (menu 2).");
            return;
        }

        MataKuliah mk = arsip.cariMataKuliah(bacaTeksWajib("Masukkan kode mata kuliah: "));
        if (mk == null) {
            System.out.println("Mata kuliah tidak ditemukan.");
            return;
        }

        String judul = bacaTeksWajib("Judul materi      : ");
        int pertemuan;
        do {
            pertemuan = bacaAngka("Nomor pertemuan   : ");
            if (pertemuan < 1) System.out.println("Nomor pertemuan minimal 1.");
        } while (pertemuan < 1);
        String deskripsi = bacaTeks("Deskripsi         : ");

        System.out.println("Jenis sumber materi:");
        System.out.println("1. File di komputer");
        System.out.println("2. Tautan (Google Drive / Vclass / lainnya)");
        int jenis = bacaAngka("Pilih jenis (1/2): ");

        Materi baru;
        if (jenis == 1) {
            String lokasi = bacaTeksWajib("Lokasi file (mis. C:\\Kuliah\\PBO.pdf): ");
            baru = new MateriFile(arsip.buatId(), judul, deskripsi, pertemuan, mk, lokasi);
        } else if (jenis == 2) {
            String platform = bacaTeksWajib("Platform (Google Drive/Vclass/dll): ");
            String url = bacaTeksWajib("URL tautan: ");
            baru = new MateriTautan(arsip.buatId(), judul, deskripsi, pertemuan, mk, url, platform);
        } else {
            System.out.println("Jenis tidak valid. Penyimpanan dibatalkan.");
            return;
        }

        arsip.tambahMateri(baru);
        System.out.println("Materi berhasil disimpan dengan ID " + baru.getId() + ".");
    }

    // ---------------- 4. Cari Materi ----------------
    private static void cariMateri() {
        String kata = bacaTeksWajib("Masukkan judul/kata kunci: ");
        ArrayList<Materi> hasil = arsip.cariMateri(kata);
        tampilkanHasil(hasil);
    }

    // ---------------- 5. Filter Materi ----------------
    private static void filterMateri() {
        MataKuliah mk = null;
        String kode = bacaTeks("Kode mata kuliah (kosongkan untuk semua): ");
        if (!kode.isEmpty()) {
            mk = arsip.cariMataKuliah(kode);
            if (mk == null) {
                System.out.println("Mata kuliah tidak ditemukan.");
                return;
            }
        }
        int pert = bacaAngka("Nomor pertemuan (0 untuk semua): ");
        tampilkanHasil(arsip.filterMateri(mk, pert));
    }

    private static void tampilkanHasil(ArrayList<Materi> hasil) {
        if (hasil.isEmpty()) {
            System.out.println("Materi tidak ditemukan.");
            return;
        }
        System.out.println("Ditemukan " + hasil.size() + " materi:");
        for (Materi m : hasil) {
            m.tampilkanBaris();
        }
    }

    // ---------------- 6. Detail Materi ----------------
    private static void lihatDetailMateri() {
        int id = bacaAngka("Masukkan ID materi: ");
        Materi m = arsip.cariMateriById(id);
        if (m == null) {
            System.out.println("Materi tidak ditemukan.");
        } else {
            System.out.println("\n------- DETAIL MATERI -------");
            m.tampilkanDetail();
        }
    }

    // ---------------- 7. Ubah Materi ----------------
    private static void ubahMateri() {
        int id = bacaAngka("Masukkan ID materi yang diubah: ");
        Materi m = arsip.cariMateriById(id);
        if (m == null) {
            System.out.println("Materi tidak ditemukan.");
            return;
        }

        System.out.println("Kosongkan input jika tidak ingin mengubah.");

        String judul = bacaTeks("Judul baru     [" + m.getJudul() + "]: ");
        if (!judul.isEmpty()) m.setJudul(judul);

        String deskripsi = bacaTeks("Deskripsi baru [" + m.getDeskripsi() + "]: ");
        if (!deskripsi.isEmpty()) m.setDeskripsi(deskripsi);

        String pert = bacaTeks("Pertemuan baru [" + m.getNomorPertemuan() + "]: ");
        if (!pert.isEmpty()) {
            try {
                int n = Integer.parseInt(pert);
                if (n >= 1) m.setNomorPertemuan(n);
                else System.out.println("Nomor pertemuan tidak valid, tidak diubah.");
            } catch (NumberFormatException e) {
                System.out.println("Bukan angka, nomor pertemuan tidak diubah.");
            }
        }

        // perilaku berbeda sesuai subclass
        if (m instanceof MateriFile) {
            MateriFile mf = (MateriFile) m;
            String lokasi = bacaTeks("Lokasi file baru [" + mf.getLokasiFile() + "]: ");
            if (!lokasi.isEmpty()) mf.setLokasiFile(lokasi);
        } else if (m instanceof MateriTautan) {
            MateriTautan mt = (MateriTautan) m;
            String url = bacaTeks("URL baru [" + mt.getUrl() + "]: ");
            if (!url.isEmpty()) mt.setUrl(url);
        }

        System.out.println("Materi berhasil diperbarui.");
    }

    // ---------------- 8. Hapus Materi ----------------
    private static void hapusMateri() {
        int id = bacaAngka("Masukkan ID materi yang dihapus: ");
        Materi m = arsip.cariMateriById(id);
        if (m == null) {
            System.out.println("Materi tidak ditemukan.");
            return;
        }
        String yakin = bacaTeks("Hapus \"" + m.getJudul() + "\"? (y/n): ");
        if (yakin.equalsIgnoreCase("y")) {
            arsip.hapusMateri(id);
            System.out.println("Materi berhasil dihapus.");
        } else {
            System.out.println("Penghapusan dibatalkan.");
        }
    }

    // ---------------- 9. Catatan Pribadi ----------------
    private static void tambahCatatan() {
        int id = bacaAngka("Masukkan ID materi: ");
        Materi m = arsip.cariMateriById(id);
        if (m == null) {
            System.out.println("Materi tidak ditemukan.");
            return;
        }
        String catatan = bacaTeksWajib("Tulis catatan/ringkasan: ");
        m.tambahCatatan(catatan);
        System.out.println("Catatan berhasil ditambahkan.");
    }
}