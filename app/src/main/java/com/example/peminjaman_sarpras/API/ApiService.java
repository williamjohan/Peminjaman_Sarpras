package com.example.peminjaman_sarpras.API;

import com.example.peminjaman_sarpras.model.DetailGambarRuangan_Model;
import com.example.peminjaman_sarpras.model.ListPeminjaman_Model;
import com.example.peminjaman_sarpras.model.Pemesanan_Model;
import com.example.peminjaman_sarpras.model.Ruangan_Model;
import com.example.peminjaman_sarpras.response.Pemesanan_Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @GET(ApiURL.GET_LIST_PEMINJAMAN)
    Call<List<ListPeminjaman_Model>> getListPeminjaman();

    @GET(ApiURL.GET_PEMESANAN)
    Call<Pemesanan_Model> getPemesanan(@Query("id") int id);

    //get pemesanan by status
    @GET(ApiURL.GET_PEMESANAN)
    Call<List<Pemesanan_Model>> getPemesananByStatus(@Query("status") String status);

    //ambil isi ruangan erdasarkan id list
    @GET(ApiURL.GET_RUANGAN)
    Call<List<Ruangan_Model>> getRuangan(@Query("id_list") int id_list);

    //ambil isi ruangan berdasarkan id ruangan
    @GET(ApiURL.GET_RUANGAN)
    Call<Ruangan_Model> getRuanganById(@Query("id_ruangan") int id_ruangan);




    @GET(ApiURL.GET_DETAIL_GAMBAR_RUANGAN)
    Call<List<DetailGambarRuangan_Model>> getDetailGambarRuangan(@Query("id_ruangan") int idRuangan);


    //--------Segment Pemesanan--------///
    @POST(ApiURL.POST_PEMESANAN)
    Call<Pemesanan_Response> postPemesanan(@Body Pemesanan_Model pemesanan);

    @POST(ApiURL.CANCEL_PEMESANAN)
    Call<Pemesanan_Response> cancelPemesanan(@Body Pemesanan_Model cancelpemesanan);

}
