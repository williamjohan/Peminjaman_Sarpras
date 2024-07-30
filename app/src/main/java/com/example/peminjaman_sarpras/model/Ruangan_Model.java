package com.example.peminjaman_sarpras.model;

import com.google.gson.annotations.SerializedName;

public class Ruangan_Model {

    @SerializedName("idlist")
    private int idlist; // foreign key

    @SerializedName("idruangan")
    private int idruangan; // primary key

    @SerializedName("namaruangan")
    private String namaruangan;

    @SerializedName("gambar")
    private String gambar;

    @SerializedName("lokasiruangan")
    private String lokasiruangan;

    @SerializedName("hargaruangan")
    private int hargaruangan;

    public Ruangan_Model(int idlist, int idruangan, String namaruangan, String gambar, String lokasiruangan, int hargaruangan) {
        this.idlist = idlist;
        this.idruangan = idruangan;
        this.namaruangan = namaruangan;
        this.gambar = gambar;
        this.lokasiruangan = lokasiruangan;
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

    public String getNamaruangan() {
        return namaruangan;
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
}
