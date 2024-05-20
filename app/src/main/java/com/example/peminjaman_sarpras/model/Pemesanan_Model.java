package com.example.peminjaman_sarpras.model;

public class Pemesanan_Model {

    int idpemesanan ; //primary key
    int idRuangan; //foreign key
    int hargaruangan ; //dapat dari join table
    String namaruangan; //dapat dari join table
    String gambarruangan;
    String statuspemesanan ;

//    String jampesanan; //ini harus nya input
//    String tanggalpesanan; //ini harusnya input


    public Pemesanan_Model(int idpemesanan, int idRuangan, int hargaruangan,String statuspemesanan, String namaruangan, String gambarruangan) {
        this.idpemesanan = idpemesanan;
        this.statuspemesanan = statuspemesanan;
        this.idRuangan = idRuangan;
        this.hargaruangan = hargaruangan;
        this.namaruangan = namaruangan;
        this.gambarruangan = gambarruangan;
    }

    public int getIdpemesanan() {
        return idpemesanan;
    }

    public void setIdpemesanan(int idpemesanan) {
        this.idpemesanan = idpemesanan;
    }

    public int getIdRuangan() {
        return idRuangan;
    }

    public void setIdRuangan(int idRuangan) {
        this.idRuangan = idRuangan;
    }

    public int getHargaruangan() {
        return hargaruangan;
    }

    public void setHargaruangan(int hargaruangan) {
        this.hargaruangan = hargaruangan;
    }

    public String getNamaruangan() {
        return namaruangan;
    }

    public void setNamaruangan(String namaruangan) {
        this.namaruangan = namaruangan;
    }

    public String getGambarruangan() {
        return gambarruangan;
    }

    public void setGambarruangan(String gambarruangan) {
        this.gambarruangan = gambarruangan;
    }

    public String getStatuspemesanan() {
        return statuspemesanan;
    }

    public void setStatuspemesanan(String statuspemesanan) {
        this.statuspemesanan = statuspemesanan;
    }
}
