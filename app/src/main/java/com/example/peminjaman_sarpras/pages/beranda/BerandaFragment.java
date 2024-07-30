package com.example.peminjaman_sarpras.pages.beranda;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.peminjaman_sarpras.API.ApiClient;
import com.example.peminjaman_sarpras.API.ApiService;
import com.example.peminjaman_sarpras.MainActivity;
import com.example.peminjaman_sarpras.R;
import com.example.peminjaman_sarpras.adapter.ListPeminjamanAdapter;
import com.example.peminjaman_sarpras.model.ListPeminjaman_Model;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BerandaFragment extends Fragment {
    private EditText searchedittext ;
    private RecyclerView recyclerView;
    private ListPeminjamanAdapter contendapter;
    private List<ListPeminjaman_Model> filteredList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_beranda, container, false);

        searchedittext = view.findViewById(R.id.ETsearch);
        recyclerView =view.findViewById(R.id.RVberanda);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));


        //Inisiasi Adapter dengan list kosong
        filteredList = new ArrayList<>(); //
        contendapter = new ListPeminjamanAdapter(filteredList,getContext());
        recyclerView.setAdapter(contendapter);


        fetchListPeminjaman();


//        //memasukkan isi listcontent ke adapter
//        contendapter = new ListPeminjamanAdapter(filteredList,getContext());
//        recyclerView.setAdapter(contendapter);

        //menambahkan Textwatcher untuk pencarian
        searchedittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return  view;
    }


    private void fetchListPeminjaman() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<List<ListPeminjaman_Model>> call = apiService.getListPeminjaman();
        call.enqueue(new Callback<List<ListPeminjaman_Model>>() {
            @Override
            public void onResponse(Call<List<ListPeminjaman_Model>> call, Response<List<ListPeminjaman_Model>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ListPeminjaman_Model> list = response.body();
//                    Log.d(TAG, "Response JSON: " + response.body());
//                    Log.d(TAG, "Received Data: " + new Gson().toJson(list)); // Log data received
                    filteredList.clear();
                    filteredList.addAll(list);
                    contendapter.updateData(filteredList);
                } else {
//                    Log.d(TAG, "Response failed: " + response.message());
                }
            }


            @Override
            public void onFailure(Call<List<ListPeminjaman_Model>> call, Throwable t) {
//                Log.d(TAG, "API call failed: " + t.getMessage());
            }
        });
    }

    private void filter(String text) {
        List<ListPeminjaman_Model> temporaryList = new ArrayList<>();
        if (text.isEmpty()) {
            temporaryList.addAll(filteredList); // Menggunakan filteredList yang sudah diisi dari API
        } else {
            for (ListPeminjaman_Model item : filteredList) {
                if (item.getNamacontent().toLowerCase().contains(text.toLowerCase())) {
                    temporaryList.add(item);
                }
            }
        }
        contendapter.updateData(temporaryList); // Update data pada adapter
    }


    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setStatusBarColor(ContextCompat.getColor(getContext(), R.color.getstarted));
        ((MainActivity) getActivity()).setStatusBarTextDark(false);
    }
}
