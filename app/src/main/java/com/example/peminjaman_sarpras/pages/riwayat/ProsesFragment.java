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

public class ProsesFragment extends Fragment {
    private RecyclerView recyclerView;

    private PemesananAdapter pemesananAdapter;
    private int idRuangan;
    private ViewStub nodataview;
    private DBHelper db;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DBHelper(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_proses, container, false);
        recyclerView = view.findViewById(R.id.RVproses);
        nodataview = view.findViewById(R.id.VSnodata);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //inisiasi class dbhelper


        if (getArguments() != null) {
            idRuangan = getArguments().getInt("idRuangan", -1);
        }
        loadDataAndUpdateView();


        return view;
    }

    private void loadDataAndUpdateView() {

        //memanggil isi data di dbhelper ditampung di listcontent
        List<Pemesanan_Model> listpemesanan = db.getPemesananByStatus("ON");

        if(listpemesanan.isEmpty()){
            recyclerView.setVisibility(View.GONE);
            nodataview.setVisibility(View.VISIBLE);
        }else{
            recyclerView.setVisibility(View.VISIBLE);
            nodataview.setVisibility(View.GONE);
            //memasukkan isi listcontent ke adapter
            pemesananAdapter = new PemesananAdapter(listpemesanan,getContext(),"fragmentproses");
            recyclerView.setAdapter(pemesananAdapter);

        }
    }

//    public void cancelpesanan() {
//        // After deletion, load data and update view
//        loadDataAndUpdateView();
//    }

}