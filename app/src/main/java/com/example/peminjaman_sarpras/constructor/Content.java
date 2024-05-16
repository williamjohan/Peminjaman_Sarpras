package com.example.peminjaman_sarpras.constructor;

public class Content {
    private String namacontent;
    private String jumlahvenue;
    private String imageresname;

    public Content(String namacontent, String jumlahvenue, String imageresname) {
        this.namacontent = namacontent;
        this.jumlahvenue = jumlahvenue;
        this.imageresname = imageresname;
    }

    public String getNamacontent() {
        return namacontent;
    }

    public void setNamacontent(String namacontent) {
        this.namacontent = namacontent;
    }

    public String getJumlahvenue() {
        return jumlahvenue;
    }

    public void setJumlahvenue(String jumlahvenue) {
        this.jumlahvenue = jumlahvenue;
    }

    public String getImageresname() {
        return imageresname;
    }

    public void setImageresname(String imageresname) {
        this.imageresname = imageresname;
    }
}
