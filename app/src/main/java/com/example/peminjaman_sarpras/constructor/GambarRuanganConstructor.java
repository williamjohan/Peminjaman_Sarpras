package com.example.peminjaman_sarpras.constructor;

public class GambarRuanganConstructor {

    private int idgambar;
    private int idruangan;
    private String resgambar;

    public GambarRuanganConstructor(int idruangan, int idgambar, String resgambar) {
        this.idgambar = idgambar;
        this.idruangan = idruangan;
        this.resgambar = resgambar;
    }

    public int getIdgambar() {
        return idgambar;
    }

    public void setIdgambar(int idgambar) {
        this.idgambar = idgambar;
    }

    public int getIdruangan() {
        return idruangan;
    }

    public void setIdruangan(int idruangan) {
        this.idruangan = idruangan;
    }

    public String getResgambar() {
        return resgambar;
    }

    public void setResgambar(String resgambar) {
        this.resgambar = resgambar;
    }
}
