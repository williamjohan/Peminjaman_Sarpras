package com.example.peminjaman_sarpras.model;

public class ListPeminjaman_Model {
    private int id; //primary key
    private String namacontent;
    private String imageresname;

    private int jumlahruangan;


    public ListPeminjaman_Model(int id, String namacontent, String imageresname , int jumlahruangan) {
        this.namacontent = namacontent;
        this.imageresname = imageresname;
        this.id = id;
        this.jumlahruangan = jumlahruangan;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJumlahruangan() {
        return jumlahruangan;
    }

    public void setJumlahruangan(int jumlahruangan) {
        this.jumlahruangan = jumlahruangan;
    }
}
