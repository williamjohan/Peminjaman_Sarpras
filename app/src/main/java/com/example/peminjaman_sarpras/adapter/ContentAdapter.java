package com.example.peminjaman_sarpras.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.peminjaman_sarpras.ContentSectionPage.SubContent;
import com.example.peminjaman_sarpras.R;
import com.example.peminjaman_sarpras.constructor.ContentConstructor;
import com.example.peminjaman_sarpras.constructor.SubContentConstructor;

import java.util.List;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder> {

    private List<ContentConstructor> listcontent;
    private List<SubContentConstructor> sublistcontent;





    private Context context;

    public ContentAdapter(List<ContentConstructor> listcontent, Context context) {
        this.listcontent = listcontent;
        this.context = context;

    }

    @NonNull
    @Override
    public ContentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listcontent,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ContentAdapter.ViewHolder holder, int position) {
    // buat mentransfer nilai dari content
        ContentConstructor classcontent = listcontent.get(position);


        //transfer title dan jumlah venue ke holder
        holder.titletextview.setText(classcontent.getNamacontent());
        holder.jumlahruangannya.setText(" Tersedia " + classcontent.getJumlahruangan() + " Venue");

        //mentransfer gambar ke holder , tapi dibuat dulu penampung gambarnya
        int imagecontent = context.getResources().getIdentifier(classcontent.getImageresname(),"drawable",context.getPackageName());
        holder.ImageView.setImageResource(imagecontent);


        //TODO On click ketika item view diklik
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                int position = holder.getAdapterPosition();

                ContentConstructor content = listcontent.get(position);


                String namajudul = listcontent.get(position).getNamacontent();
                int idlistcontent = listcontent.get(position).getId();

                Intent intent = new Intent(context, SubContent.class);
                intent.putExtra("namacontent",namajudul);
                intent.putExtra("idlistcontent",idlistcontent);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listcontent.size();
    }


    //buat class view untuk menerima content
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView titletextview , jumlahruangannya;
        public android.widget.ImageView ImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titletextview = itemView.findViewById(R.id.Tvjudulcontent);
            jumlahruangannya = itemView.findViewById(R.id.Tvquotaruangan);
            ImageView = itemView.findViewById(R.id.imgcontent);
        }
    }
}
