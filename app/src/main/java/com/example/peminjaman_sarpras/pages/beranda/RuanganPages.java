package com.example.peminjaman_sarpras.pages.beranda;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.peminjaman_sarpras.API.ApiClient;
import com.example.peminjaman_sarpras.API.ApiService;
import com.example.peminjaman_sarpras.R;
import com.example.peminjaman_sarpras.adapter.RuanganAdapter;
import com.example.peminjaman_sarpras.model.Ruangan_Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RuanganPages extends AppCompatActivity {

    private TextView judulbar;
    private ImageView imgback;

    private RecyclerView recyclerView;
    private RuanganAdapter subcontentadapter;
    private ApiService apiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ruangan);

        //menghilangkan action bar
        getSupportActionBar().hide();

        //init var
        judulbar = findViewById(R.id.judulbar);
        imgback = findViewById(R.id.backicon);
        recyclerView = findViewById(R.id.RVsubcontent);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Bundle extras = getIntent().getExtras();
        judulbar.setText(extras.getString("namacontent"));
        int idcontent = extras.getInt("idlistcontent");

        //inisiasi ApiService
        apiService = ApiClient.getClient().create(ApiService.class);

        //memanggil data dari API
        fetchRuangan(idcontent);


//        //inisiasi class dbhelper
//        DBHelper db = new DBHelper(this);
//        //memanggil isi data di dbhelper ditampung di listcontent
//        List<Ruangan_Model> listsubcontent = db.getallruangan(idcontent);


        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }

    private void fetchRuangan(int idcontent) {
        Call<List<Ruangan_Model>> call = apiService.getRuangan(idcontent);
        call.enqueue(new Callback<List<Ruangan_Model>>() {
            @Override
            public void onResponse(Call<List<Ruangan_Model>> call, Response<List<Ruangan_Model>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Ruangan_Model> listsubcontent = response.body();
                    subcontentadapter = new RuanganAdapter(listsubcontent, RuanganPages.this);
                    recyclerView.setAdapter(subcontentadapter);
                } else {
                    // Tangani jika response tidak berhasil
                    Toast.makeText(RuanganPages.this, "Data tidak ditemukan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Ruangan_Model>> call, Throwable t) {
                // Tangani kegagalan
                Toast.makeText(RuanganPages.this, "Terjadi kesalahan: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}