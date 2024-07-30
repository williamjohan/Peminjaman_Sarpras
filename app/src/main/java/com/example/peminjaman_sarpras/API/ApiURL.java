package com.example.peminjaman_sarpras.API;

public class ApiURL {
    // Base URL API

    //versi hosting
//    public static final String BASE_URL = "http://api.williamjohanp.com/";

    //versi localhost
    public static final String BASE_URL = "http://192.168.137.183/API/";


    // Endpoint Get
    public static final String GET_LIST_PEMINJAMAN = "get_listpeminjaman.php";
    public static final String GET_PEMESANAN = "get_pemesanan.php";
    public static final String GET_RUANGAN = "get_ruangan.php";
    public static final String GET_DETAIL_GAMBAR_RUANGAN = "get_detailgambarruangan.php";

    // Endpoint Post
    //--mendapatkan datapemesanan
    public static final String POST_PEMESANAN = "post_pemesanan.php";
    public static final String CANCEL_PEMESANAN = "cancel_pemesanan.php";



}
