package com.example.peminjaman_sarpras.ui.beranda;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.peminjaman_sarpras.R;
import com.example.peminjaman_sarpras.adapter.ContentAdapter;
import com.example.peminjaman_sarpras.constructor.ContentConstructor;
import com.example.peminjaman_sarpras.database.DBHelper;

import java.util.List;


public class BerandaFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView namaprofile;
    private ContentAdapter contendapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        Log.d("BerandaFragment", "onViewCreated");

        View view = inflater.inflate(R.layout.fragment_beranda, container, false);
        namaprofile = view.findViewById(R.id.namaprofile);


        recyclerView =view.findViewById(R.id.RViewBeranda);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

        //inisiasi class dbhelper
        DBHelper db = new DBHelper(getContext());
        //memanggil isi data di dbhelper ditampung di listcontent
        List<ContentConstructor> listcontent = db.getallcontents();



        //memasukkan isi listcontent ke adapter
        contendapter = new ContentAdapter(listcontent,getContext());
        recyclerView.setAdapter(contendapter);


        return  view;
    }

}