package com.example.peminjaman_sarpras.response;

import com.example.peminjaman_sarpras.model.Pemesanan_Model;

public class Pemesanan_Response {
    private String status;
    private String message;
    private Pemesanan_Model pemesanan;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Pemesanan_Model getPemesanan() {
        return pemesanan;
    }

    public void setPemesanan(Pemesanan_Model pemesanan) {
        this.pemesanan = pemesanan;
    }
}
