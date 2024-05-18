package com.example.peminjaman_sarpras.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.peminjaman_sarpras.R;
import com.example.peminjaman_sarpras.allpages.SubDetailContent;
import com.example.peminjaman_sarpras.constructor.SubContentConstructor;

import java.util.ArrayList;
import java.util.List;

public class SubContentAdapter extends RecyclerView.Adapter<SubContentAdapter.ViewHolder> {

    private List<SubContentConstructor> subcontentlist = new ArrayList<>();
    private Context context;

    public SubContentAdapter(List<SubContentConstructor> subcontentlist, Context context) {
        this.subcontentlist = subcontentlist;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_sub_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //transfer nilai dari subcontent
        SubContentConstructor subcontent = subcontentlist.get(position);

        //transfer nilai dari subcontent
        holder.namaruangan.setText(subcontent.getNamaruangan());
        holder.hargaruangan.setText("Rp." + subcontent.getHargaruangan() + " /Jam");
        holder.lokasiruangan.setText(subcontent.getLokasiruangan());

        //transfer gambar ke holder
        int imageResource = context.getResources().getIdentifier(subcontent.getGambar(), "drawable", context.getPackageName());
        holder.gambarruangan.setImageResource(imageResource);


        //TODO ketika item di adapter di klik
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                int idruangan = subcontentlist.get(position).getIdruangan();
                String namaRuangan = subcontentlist.get(position).getNamaruangan();
                int hargaRuangan = subcontentlist.get(position).getHargaruangan();


                Intent intent = new Intent(context, SubDetailContent.class);
                // Buka SubDetailContentActivity dan kirim data ke activity tersebut
                intent.putExtra("nama_ruangan", namaRuangan);
                intent.putExtra("id_ruangan", idruangan);
                intent.putExtra("harga_sewa",hargaRuangan);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return subcontentlist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView namaruangan,hargaruangan,lokasiruangan;
        private ImageView gambarruangan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            namaruangan = itemView.findViewById(R.id.Tvnamaruangan);
            hargaruangan = itemView.findViewById(R.id.tvhargasewa);
            lokasiruangan = itemView.findViewById(R.id.Tvlokasiruangan);
            gambarruangan = itemView.findViewById(R.id.Img_resgambar);
        }
    }
}
