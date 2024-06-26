package com.example.peminjaman_sarpras.model;

public class Ruangan_Model {

    private int idlist; //foreign key
    private int idruangan; //primary key
    private String namaruangan;
    private String gambar;
    private String lokasiruangan;
    private int hargaruangan;

    public Ruangan_Model(int idlist, int idruangan, String namaruangan, String gambar, String lokasiruangan, int hargaruangan) {
        this.idlist= idlist;
        this.idruangan = idruangan;
        this.namaruangan = namaruangan;
        this.gambar = gambar;
        this.lokasiruangan = lokasiruangan;
        this.hargaruangan = hargaruangan;
    }

    public String getNamaruangan() {return namaruangan;
    }

    public void setNamaruangan(String namaruangan) {
        this.namaruangan = namaruangan;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getLokasiruangan() {
        return lokasiruangan;
    }

    public void setLokasiruangan(String lokasiruangan) {
        this.lokasiruangan = lokasiruangan;
    }

    public int getHargaruangan() {
        return hargaruangan;
    }

    public void setHargaruangan(int hargaruangan) {
        this.hargaruangan = hargaruangan;
    }

    public int getIdlist() {
        return idlist;
    }

    public void setIdlist(int idlist) {
        this.idlist = idlist;
    }

    public int getIdruangan() {
        return idruangan;
    }

    public void setIdruangan(int idruangan) {
        this.idruangan = idruangan;
    }
}
