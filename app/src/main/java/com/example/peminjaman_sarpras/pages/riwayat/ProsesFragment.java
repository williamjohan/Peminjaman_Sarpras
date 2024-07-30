package com.example.peminjaman_sarpras.pages.riwayat;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.peminjaman_sarpras.API.ApiClient;
import com.example.peminjaman_sarpras.API.ApiService;
import com.example.peminjaman_sarpras.R;
import com.example.peminjaman_sarpras.adapter.PemesananAdapter;
import com.example.peminjaman_sarpras.model.Pemesanan_Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProsesFragment extends Fragment {
    private RecyclerView recyclerView;
    private PemesananAdapter pemesananAdapter;
    private int idRuangan;
    private ViewStub nodataview;
    private ApiService apiService;
    private Pemesanan_Model pemesananModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiService = ApiClient.getClient().create(ApiService.class);
        if (apiService != null) {
//            Log.e("ProsesFragment", "ApiService is success");
        }
        if (getArguments() != null) {
            int idRuangan = getArguments().getInt("idRuangan", -1);
//            Log.d("ProsesFragment", "idRuangan: " + idRuangan);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_proses, container, false);
        recyclerView = view.findViewById(R.id.RVproses);
        nodataview = view.findViewById(R.id.VSnodata);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        loadDataAndUpdateView("Menunggu Konfirmasi");
        return view;
    }

    private void loadDataAndUpdateView(String status) {
        apiService.getPemesananByStatus(status).enqueue(new Callback<List<Pemesanan_Model>>() {
            @Override
            public void onResponse(Call<List<Pemesanan_Model>> call, Response<List<Pemesanan_Model>> response) {
                List<Pemesanan_Model> listpemesanan = response.body();
                if (listpemesanan == null || listpemesanan.isEmpty()) {
                    recyclerView.setVisibility(View.GONE);
                    nodataview.setVisibility(View.VISIBLE);
                } else {
                    recyclerView.setVisibility(View.VISIBLE);
                    nodataview.setVisibility(View.GONE);
                    if (pemesananAdapter == null) {
                        pemesananAdapter = new PemesananAdapter(listpemesanan, getContext(), "fragmentproses");
                        recyclerView.setAdapter(pemesananAdapter);
                    } else {
                        pemesananAdapter.updateData(listpemesanan);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Pemesanan_Model>> call, Throwable throwable) {
                Log.e("ProsesFragment", "Error: " + throwable.getMessage());
                Toast.makeText(getContext(), "Gagal memuat data: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }



    private void refreshPemesananList() {
        loadDataAndUpdateView("Menunggu Konfirmasi");
    }

    @Override
    public void onResume() {
        super.onResume();
        loadDataAndUpdateView("Menunggu Konfirmasi");
    }
}

