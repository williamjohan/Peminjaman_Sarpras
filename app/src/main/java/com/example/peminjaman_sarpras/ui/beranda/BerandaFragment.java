package com.example.peminjaman_sarpras.ui.beranda;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.peminjaman_sarpras.DBHelper;
import com.example.peminjaman_sarpras.R;
import com.example.peminjaman_sarpras.adapter.ContentAdapter;
import com.example.peminjaman_sarpras.constructor.Content;

import java.util.List;


public class BerandaFragment extends Fragment {

    private RecyclerView recyclerView;
    private ContentAdapter contendapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_beranda, container, false);

        recyclerView =view.findViewById(R.id.RViewBeranda);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

        //inisiasi class dbhelper
        DBHelper db = new DBHelper(getContext());
        //memanggil isi data di dbhelper ditampung di listcontent
        List<Content> listcontent = db.getallcontents();

        //memasukkan isi listcontent ke adapter
        contendapter = new ContentAdapter(listcontent,getContext());
        recyclerView.setAdapter(contendapter);


        return  view;
    }

}