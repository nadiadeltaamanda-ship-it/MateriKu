/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.materiku;

/**
 *
 * @author ASUS
 */
public class MateriTautan extends Materi {
    private String url;
    private String platform;

    public MateriTautan(int id, String judul, String deskripsi,
                        int nomorPertemuan, MataKuliah mataKuliah,
                        String url, String platform) {
        super(id, judul, deskripsi, nomorPertemuan, mataKuliah);
        this.url = url;
        this.platform = platform;
    }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getPlatform() { return platform; }
    public void setPlatform(String platform) { this.platform = platform; }

    @Override
    public String getJenis() { return "Tautan (" + platform + ")"; }

    @Override
    public String getSumber() { return url; }
}