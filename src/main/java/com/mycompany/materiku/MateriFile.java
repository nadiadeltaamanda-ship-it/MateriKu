/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.materiku;

/**
 *
 * @author ASUS
 */
public class MateriFile extends Materi {
    private String lokasiFile;

    public MateriFile(int id, String judul, String deskripsi,
                      int nomorPertemuan, MataKuliah mataKuliah,
                      String lokasiFile) {
        super(id, judul, deskripsi, nomorPertemuan, mataKuliah);
        this.lokasiFile = lokasiFile;
    }

    public String getLokasiFile() { return lokasiFile; }
    public void setLokasiFile(String lokasiFile) { this.lokasiFile = lokasiFile; }

    @Override
    public String getJenis() { return "File Lokal"; }

    @Override
    public String getSumber() { return lokasiFile; }
}
