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

public class ArsipMateri {
    private ArrayList<MataKuliah> daftarMataKuliah = new ArrayList<>();
    private ArrayList<Materi> daftarMateri = new ArrayList<>();
    private int idBerikutnya = 1;

    public ArrayList<MataKuliah> getDaftarMataKuliah() { return daftarMataKuliah; }
    public ArrayList<Materi> getDaftarMateri() { return daftarMateri; }

    public int buatId() { return idBerikutnya++; }

    // ---------- Mata Kuliah ----------
    public boolean tambahMataKuliah(MataKuliah mk) {
        if (cariMataKuliah(mk.getKode()) != null) {
            return false; // kode sudah dipakai
        }
        daftarMataKuliah.add(mk);
        return true;
    }

    public MataKuliah cariMataKuliah(String kode) {
        for (MataKuliah mk : daftarMataKuliah) {
            if (mk.getKode().equalsIgnoreCase(kode)) {
                return mk;
            }
        }
        return null;
    }

    public boolean hapusMataKuliah(String kode) {
        MataKuliah mk = cariMataKuliah(kode);
        if (mk == null) return false;
        // hapus juga semua materi milik mata kuliah tersebut
        daftarMateri.removeIf(m -> m.getMataKuliah() == mk);
        daftarMataKuliah.remove(mk);
        return true;
    }

    // ---------- Materi ----------
    public void tambahMateri(Materi m) { daftarMateri.add(m); }

    public Materi cariMateriById(int id) {
        for (Materi m : daftarMateri) {
            if (m.getId() == id) return m;
        }
        return null;
    }

    public boolean hapusMateri(int id) {
        Materi m = cariMateriById(id);
        if (m == null) return false;
        daftarMateri.remove(m);
        return true;
    }

    public ArrayList<Materi> cariMateri(String kataKunci) {
        ArrayList<Materi> hasil = new ArrayList<>();
        for (Materi m : daftarMateri) {
            if (m.cocokDenganKataKunci(kataKunci)) hasil.add(m);
        }
        return hasil;
    }

    public ArrayList<Materi> filterMateri(MataKuliah mk, int nomorPertemuan) {
        // nomorPertemuan = 0 berarti tidak difilter berdasarkan pertemuan
        ArrayList<Materi> hasil = new ArrayList<>();
        for (Materi m : daftarMateri) {
            boolean cocokMk = (mk == null) || (m.getMataKuliah() == mk);
            boolean cocokPert = (nomorPertemuan == 0) || (m.getNomorPertemuan() == nomorPertemuan);
            if (cocokMk && cocokPert) hasil.add(m);
        }
        return hasil;
    }
}