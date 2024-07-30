package com.example.peminjaman_sarpras.model;

import com.google.gson.annotations.SerializedName;

public class Pemesanan_Model {
    @SerializedName("id_pemesanan")
    private int idPemesanan;

    @SerializedName("id_ruangan")
    private int idRuangan;

    @SerializedName("status_pemesanan")
    private String statusPemesanan;

    public Pemesanan_Model(int idPemesanan, int idRuangan, String statusPemesanan) {
        this.idPemesanan = idPemesanan;
        this.idRuangan = idRuangan;
        this.statusPemesanan = statusPemesanan;
    }

    public int getIdPemesanan() {
        return idPemesanan;
    }

    public void setIdPemesanan(int idPemesanan) {
        this.idPemesanan = idPemesanan;
    }

    public int getIdRuangan() {
        return idRuangan;
    }

    public void setIdRuangan(int idRuangan) {
        this.idRuangan = idRuangan;
    }

    public String getStatusPemesanan() {
        return statusPemesanan;
    }

    public void setStatusPemesanan(String statusPemesanan) {
        this.statusPemesanan = statusPemesanan;
    }
}
