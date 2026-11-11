/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.materiku;

/**
 *
 * @author ASUS
 */
import java.util.ArrayList;

public class Materi {
    private int id;
    private String judul;
    private String deskripsi;
    private int nomorPertemuan;
    private MataKuliah mataKuliah;
    private ArrayList<String> catatan = new ArrayList<>();

    public Materi(int id, String judul, String deskripsi,
                  int nomorPertemuan, MataKuliah mataKuliah) {
        this.id = id;
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.nomorPertemuan = nomorPertemuan;
        this.mataKuliah = mataKuliah;
    }

    // ---- getter & setter ----
    public int getId() { return id; }

    public String getJudul() { return judul; }
    public void setJudul(String judul) { this.judul = judul; }

    public String getDeskripsi() { return deskripsi; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }

    public int getNomorPertemuan() { return nomorPertemuan; }
    public void setNomorPertemuan(int nomorPertemuan) { this.nomorPertemuan = nomorPertemuan; }

    public MataKuliah getMataKuliah() { return mataKuliah; }
    public void setMataKuliah(MataKuliah mataKuliah) { this.mataKuliah = mataKuliah; }

    public ArrayList<String> getCatatan() { return catatan; }

    // ---- method yang akan di-override oleh subclass ----
    public String getJenis() { return "Materi Umum"; }

    public String getSumber() { return "-"; }

    public void tampilkanDetail() {
        System.out.println("ID              : " + id);
        System.out.println("Judul           : " + judul);
        System.out.println("Mata Kuliah     : " + mataKuliah);
        System.out.println("Pertemuan ke-   : " + nomorPertemuan);
        System.out.println("Jenis           : " + getJenis());
        System.out.println("Deskripsi       : " + deskripsi);
        System.out.println("Sumber          : " + getSumber());
        System.out.println("Catatan Pribadi :");
        if (catatan.isEmpty()) {
            System.out.println("   (belum ada catatan)");
        } else {
            for (int i = 0; i < catatan.size(); i++) {
                System.out.println("   " + (i + 1) + ". " + catatan.get(i));
            }
        }
    }

    public void tambahCatatan(String isi) {
        catatan.add(isi);
    }

    // dipakai untuk pencarian berdasarkan judul / kata kunci
    public boolean cocokDenganKataKunci(String kataKunci) {
        String kunci = kataKunci.toLowerCase();
        return judul.toLowerCase().contains(kunci)
                || deskripsi.toLowerCase().contains(kunci);
    }

    public void tampilkanBaris() {
        System.out.printf("[%d] %-30s | %-25s | Pert. %d | %s%n",
                id, judul, mataKuliah.getNama(), nomorPertemuan, getJenis());
    }
}