package com.example.peminjaman_sarpras.constructor;

public class SubContentConstructor {

    private int idlist;
    private String namaruangan;
    private String gambar;
    private String lokasiruangan;
    private String hargaruangan;

    public SubContentConstructor(int idlist, String namaruangan, String gambar, String lokasiruangan, String hargaruangan) {
        this.idlist= idlist;
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

    public String getHargaruangan() {
        return hargaruangan;
    }

    public void setHargaruangan(String hargaruangan) {
        this.hargaruangan = hargaruangan;
    }

    public int getIdlist() {
        return idlist;
    }

    public void setIdlist(int idlist) {
        this.idlist = idlist;
    }
}
