package com.example.peminjaman_sarpras.model;

import com.google.gson.annotations.SerializedName;

public class ListPeminjaman_Model {

    @SerializedName("id")
    private int id;

    @SerializedName("nama")
    private String namacontent;

    @SerializedName("gambar")
    private String imageresname;

    @SerializedName("jumlahruangan")
    private int jumlahruangan;

    public ListPeminjaman_Model(int id, String namacontent, String imageresname, int jumlahruangan) {
        this.id = id;
        this.namacontent = namacontent;
        this.imageresname = imageresname;
        this.jumlahruangan = jumlahruangan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamacontent() {
        return namacontent;
    }

    public void setNamacontent(String namacontent) {
        this.namacontent = namacontent;
    }

    public String getImageresname() {
        return imageresname;
    }

    public void setImageresname(String imageresname) {
        this.imageresname = imageresname;
    }

    public int getJumlahruangan() {
        return jumlahruangan;
    }

    public void setJumlahruangan(int jumlahruangan) {
        this.jumlahruangan = jumlahruangan;
    }
}
