package com.example.peminjaman_sarpras.pages.beranda;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.peminjaman_sarpras.API.ApiClient;
import com.example.peminjaman_sarpras.API.ApiService;
import com.example.peminjaman_sarpras.MainActivity;
import com.example.peminjaman_sarpras.R;
import com.example.peminjaman_sarpras.adapter.CarouselAdapter;
import com.example.peminjaman_sarpras.model.DetailGambarRuangan_Model;
import com.example.peminjaman_sarpras.model.Pemesanan_Model;
import com.example.peminjaman_sarpras.response.Pemesanan_Response;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailRuanganPages extends AppCompatActivity {
    private TextView judulruangan,hargasewa;
    private ImageView imgback;
    private ViewPager2 viewPager;
    private CarouselAdapter gambaradapter;
    private TabLayout tabindicator;
    private Button btnpesan;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_ruangan);

        // Hide action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Initialize API service
        apiService = ApiClient.getClient().create(ApiService.class);

        //innit data
        imgback = findViewById(R.id.backicon);
        judulruangan = findViewById(R.id.TVheadercontent);
        viewPager = findViewById(R.id.viewpager);
        tabindicator = findViewById(R.id.tabindicator);
        btnpesan = findViewById(R.id.BTNpesan);
        hargasewa = findViewById(R.id.TVharga);


        //fetch data dari intent
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            judulruangan.setText(extras.getString("nama_ruangan"));
            hargasewa.setText("Rp." + extras.getInt("harga_sewa") + " / Jam");
            int idRuangan = extras.getInt("id_ruangan");

            // Fetch images from API
            fetchImages(idRuangan);

            // Set up back button
            imgback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            // Set up book button
            btnpesan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bookroom(idRuangan);
                }
            });
        }
    }

    private void fetchImages(int idRuangan) {
        apiService.getDetailGambarRuangan(idRuangan).enqueue(new Callback<List<DetailGambarRuangan_Model>>() {
            @Override
            public void onResponse(Call<List<DetailGambarRuangan_Model>> call, Response<List<DetailGambarRuangan_Model>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<DetailGambarRuangan_Model> listimages = response.body();
                    if (listimages.isEmpty()) {
                        listimages.add(new DetailGambarRuangan_Model(idRuangan, 0, "default_image")); // Replace with your default image name
                    }

//                    // Log the URLs
//                    for (DetailGambarRuangan_Model image : listimages) {
//                        Log.d("DetailRuanganPages", "Image URL: " + image.getResgambar());
//                    }

                    gambaradapter = new CarouselAdapter(listimages, DetailRuanganPages.this);
                    viewPager.setAdapter(gambaradapter);

                    new TabLayoutMediator(tabindicator, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
                        @Override
                        public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                            // No need to configure text for tabs as we use drawable selector
                        }
                    }).attach();
                } else {
                    Toast.makeText(DetailRuanganPages.this, "Tidak ada gambar ditemukan.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<DetailGambarRuangan_Model>> call, Throwable t) {
                Toast.makeText(DetailRuanganPages.this, "Gagal memuat gambar.", Toast.LENGTH_SHORT).show();
                Log.e("DetailRuanganPages", "Error: " + t.getMessage());
            }
        });
    }

    public void bookroom(int idRuangan) {
        // Buat objek Pemesanan_Model
        Pemesanan_Model pemesanan = new Pemesanan_Model(0, idRuangan, "Menunggu Konfirmasi"); // id_pemesanan diisi 0 untuk sementara

        // Log data yang akan dikirim
        Log.d("BookRoom", "Pemesanan data: " + new Gson().toJson(pemesanan));

        // Mengirimkan data pemesanan ke API
        Log.d("BookRoom", "Mengirim data ke API...");
        apiService.postPemesanan(pemesanan).enqueue(new Callback<Pemesanan_Response>() {
            @Override
            public void onResponse(Call<Pemesanan_Response> call, Response<Pemesanan_Response> response) {
                if (response.isSuccessful()) {
                    Pemesanan_Response responseData = response.body();
                    if ("success".equals(responseData.getStatus())) {
                        // Mengambil id_pemesanan yang diterima dari server
                        int idPemesanan = responseData.getPemesanan().getIdPemesanan();

                        // Berhasil memesan, navigasi ke riwayat
                        Intent intent = new Intent(DetailRuanganPages.this, MainActivity.class);
                        intent.putExtra("navigateTo", "riwayat");
                        intent.putExtra("idRuangan", idRuangan);
                        intent.putExtra("idPemesanan", idPemesanan); // Menambahkan id_pemesanan
                        startActivity(intent);
                        finish();
                    } else {
                        // Menampilkan pesan error
                        String errorMessage = responseData != null ? responseData.getMessage() : "Unknown error";
                        Toast.makeText(DetailRuanganPages.this, "Gagal memesan: " + errorMessage, Toast.LENGTH_SHORT).show();
                        Log.e("BookRoom", "Error: " + errorMessage);
                    }
                } else {
                    Toast.makeText(DetailRuanganPages.this, "Respons tidak berhasil", Toast.LENGTH_SHORT).show();
                    Log.e("BookRoom", "Response not successful, Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Pemesanan_Response> call, Throwable t) {
                // Menampilkan pesan error jika terjadi kegagalan jaringan
                Toast.makeText(DetailRuanganPages.this, "Gagal menghubungi server: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("BookRoom", "Failure: " + t.getMessage());
            }
        });
    }

}
