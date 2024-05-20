package com.example.peminjaman_sarpras.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.peminjaman_sarpras.R;
import com.example.peminjaman_sarpras.model.DetailRuangan_Model;

import java.util.List;

public class CarouselAdapter extends RecyclerView.Adapter<CarouselAdapter.ViewHolder> {

    private List<DetailRuangan_Model> listimageresource;
    private Context context;

    public CarouselAdapter(List<DetailRuangan_Model> imageresource, Context context) {
        this.listimageresource = imageresource;
        this.context = context;
    }



    @NonNull
    @Override
    public CarouselAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_carousel, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarouselAdapter.ViewHolder holder, int position) {
        DetailRuangan_Model image = listimageresource.get(position);
        int imagecontent = context.getResources().getIdentifier(image.getResgambar(),"drawable",context.getPackageName());

        if (imagecontent == 0) {
            // If invalid, use the default image
            int imagedefault = R.drawable.nofound404;
            holder.viewimage.setImageResource(imagedefault);
            holder.viewimage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        } else {
            // If valid, use the provided image
            holder.viewimage.setImageResource(imagecontent);
        }


    }

    @Override
    public int getItemCount() {
        return listimageresource.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView viewimage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            viewimage = itemView.findViewById(R.id.imageViewCarousel);
        }
    }
}
