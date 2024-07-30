package com.example.peminjaman_sarpras.model;

import com.google.gson.annotations.SerializedName;

public class DetailGambarRuangan_Model {

    @SerializedName("idgambar")
    private int idgambar;

    @SerializedName("idruangan")
    private int idruangan;

    @SerializedName("resgambar")
    private String resgambar;

    // Default constructor required for Retrofit
    public DetailGambarRuangan_Model() {}

    public DetailGambarRuangan_Model(int idruangan, int idgambar, String resgambar) {
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
