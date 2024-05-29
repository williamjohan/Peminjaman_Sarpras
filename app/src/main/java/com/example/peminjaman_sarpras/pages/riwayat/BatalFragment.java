package com.example.peminjaman_sarpras.pages.riwayat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.peminjaman_sarpras.R;
import com.example.peminjaman_sarpras.adapter.PemesananAdapter;
import com.example.peminjaman_sarpras.database.DBHelper;
import com.example.peminjaman_sarpras.model.Pemesanan_Model;

import java.util.List;

public class BatalFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<Pemesanan_Model> listpemesananbatal;
    private PemesananAdapter pemesananAdapter;
    private DBHelper db;
    private ViewStub viewStub;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //inisiasi class dbhelper
        db = new DBHelper(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_batal, container, false);

        recyclerView = view.findViewById(R.id.RVBatal);
        viewStub = view.findViewById(R.id.VSnodata);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        loadData();

        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {


        listpemesananbatal = db.getPemesananByStatus("cancel");
        if (listpemesananbatal.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            viewStub.setVisibility(View.VISIBLE);
        } else {
            viewStub.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            pemesananAdapter = new PemesananAdapter(listpemesananbatal, getContext(), "fragmentbatal");
            recyclerView.setAdapter(pemesananAdapter);
        }

        listpemesananbatal = db.getPemesananByStatus("cancel");
        if (listpemesananbatal.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            viewStub.setVisibility(View.VISIBLE);
        } else {
            viewStub.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            pemesananAdapter = new PemesananAdapter(listpemesananbatal, getContext(), "fragmentbatal");
            recyclerView.setAdapter(pemesananAdapter);
        }

    }

}