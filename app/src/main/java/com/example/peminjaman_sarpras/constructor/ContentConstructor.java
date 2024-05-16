package com.example.peminjaman_sarpras.constructor;

public class ContentConstructor {
    private String namacontent;
    private String imageresname;
    private int id;
    private int jumlahruangan;


    public ContentConstructor(int id, String namacontent, String imageresname , int jumlahruangan) {
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
