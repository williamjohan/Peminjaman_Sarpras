package com.example.peminjaman_sarpras.pages.beranda;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.peminjaman_sarpras.MainActivity;
import com.example.peminjaman_sarpras.R;
import com.example.peminjaman_sarpras.adapter.ListPeminjamanAdapter;
import com.example.peminjaman_sarpras.database.DBHelper;
import com.example.peminjaman_sarpras.model.ListPeminjaman_Model;

import java.util.List;


public class BerandaFragment extends Fragment {

    private RecyclerView recyclerView;
    private ListPeminjamanAdapter contendapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_beranda, container, false);

        recyclerView =view.findViewById(R.id.RVberanda);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

        //inisiasi class dbhelper
        DBHelper db = new DBHelper(getContext());
        //memanggil isi data di dbhelper ditampung di listcontent
        List<ListPeminjaman_Model> listcontent = db.getallcontents();



        //memasukkan isi listcontent ke adapter
        contendapter = new ListPeminjamanAdapter(listcontent,getContext());
        recyclerView.setAdapter(contendapter);


        return  view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setStatusBarColor(ContextCompat.getColor(getContext(), R.color.getstarted));
        ((MainActivity) getActivity()).setStatusBarTextDark(false);
    }
}