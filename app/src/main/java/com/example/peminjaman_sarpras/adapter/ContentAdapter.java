package com.example.peminjaman_sarpras.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.peminjaman_sarpras.R;
import com.example.peminjaman_sarpras.constructor.Content;

import java.util.List;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder> {

    private List<Content> listcontent;
    private Context context;

    public ContentAdapter(List<Content> listcontent, Context context) {
        this.listcontent = listcontent;
        this.context = context;
    }

    @NonNull
    @Override
    public ContentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listcontent,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ContentAdapter.ViewHolder holder, int position) {
    // buat mentransfer nilai dari content
        Content classcontent = listcontent.get(position);
        //transfer title dan jumlah venue ke holder
        holder.titletextview.setText(classcontent.getNamacontent());
        holder.descripticontentview.setText(classcontent.getJumlahvenue());

        //mentransfer gambar ke holder , tapi dibuat dulu penampung gambarnya
        int imagecontent = context.getResources().getIdentifier(classcontent.getImageresname(),"drawable",context.getPackageName());
        holder.ImageView.setImageResource(imagecontent);


        //TODO On click ketika item view diklik
    }

    @Override
    public int getItemCount() {
        return listcontent.size();
    }


    //buat class view untuk menerima content
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView titletextview , descripticontentview;
        public android.widget.ImageView ImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titletextview = itemView.findViewById(R.id.Tvjudulcontent);
            descripticontentview = itemView.findViewById(R.id.Tvquotaruangan);
            ImageView = itemView.findViewById(R.id.imgcontent);
        }
    }
}
