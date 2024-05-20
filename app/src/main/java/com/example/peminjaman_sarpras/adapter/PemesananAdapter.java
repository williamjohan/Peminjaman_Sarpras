package com.example.peminjaman_sarpras.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.peminjaman_sarpras.R;
import com.example.peminjaman_sarpras.database.DBHelper;
import com.example.peminjaman_sarpras.model.Pemesanan_Model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PemesananAdapter extends RecyclerView.Adapter<PemesananAdapter.ViewHolder>{

    private List<Pemesanan_Model> listpemesanan = new ArrayList<>();
    private  Context context;
    private  DecimalFormat decimalformatter = new DecimalFormat("#,###,###");
    ;
    private DBHelper db;
    private String fragmentType;


    public PemesananAdapter(List<Pemesanan_Model> listpemesanan, Context context , String fragmentType) {
        this.listpemesanan = listpemesanan;
        this.context = context;
        this.fragmentType = fragmentType;
        db = new DBHelper(context);

    }

    @NonNull
    @Override
    public PemesananAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listpemesanan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PemesananAdapter.ViewHolder holder, int position) {
        position = holder.getAdapterPosition();
        Pemesanan_Model pemesanan_model = listpemesanan.get(position);
        holder.bind(pemesanan_model);


    }

    @Override
    public int getItemCount() {
        return listpemesanan.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namaitempesanan , hargaritempesanan , tanggalpesan , jamitempesanan , statuspemesanan;
        ImageView gambarpesanan ;
        Button btnaction;
        CardView Cvstatus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namaitempesanan = itemView.findViewById(R.id.TVnamaruanganpesanan);
            hargaritempesanan = itemView.findViewById(R.id.TVhargapesanan);
            tanggalpesan = itemView.findViewById(R.id.TVtanggalpesanan);
            jamitempesanan = itemView.findViewById(R.id.TVjampesanan);
            gambarpesanan = itemView.findViewById(R.id.IMGpesanan);
            btnaction = itemView.findViewById(R.id.BTNactionproses);
            statuspemesanan = itemView.findViewById(R.id.TVStatus);
            Cvstatus = itemView.findViewById(R.id.CVstatus);
        }

        public void bind(Pemesanan_Model pemesanan_model) {
            // Customize button based on fragment type
            namaitempesanan.setText(pemesanan_model.getNamaruangan());
            hargaritempesanan.setText("Rp " + decimalformatter.format(pemesanan_model.getHargaruangan()) + " /Jam");

            if ("fragmentproses".equals(fragmentType)) {
                Log.d("namaruangan_proses", pemesanan_model.getNamaruangan());
                Log.d("hargaruangan_proses", String.valueOf(pemesanan_model.getHargaruangan()));
                Log.d("gambarproses_proses", pemesanan_model.getGambarruangan());
                statuspemesanan.setText("Menunggu Pembayaran");
                btnaction.setText("Cancel");
                btnaction.setBackground(context.getResources().getDrawable(R.drawable.btn_warning_style_red));

                //TODO buat Date and Time picker layout untuk inputan
                //sementara di hold dulu , krn blm dibuat layout untuk time dan date picker.
//        holder.tanggalpesan.setText(pemesanan_model.getLokasiruangan());
//        holder.jamitempesanan.setText(pemesanan_model.getJamruangan());

                //transfer gambar ke holder
                int imageResource = context.getResources().getIdentifier(pemesanan_model.getGambarruangan(), "drawable", context.getPackageName());
                gambarpesanan.setImageResource(imageResource);

                btnaction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = getAdapterPosition();
                        int idpemesanan = pemesanan_model.getIdpemesanan();
                        db.updateStatusPemesanan(idpemesanan, "cancel");
                        pemesanan_model.setStatuspemesanan("cancel");

                        //hapus yang di list ( bukan yang di db)
                        listpemesanan.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, listpemesanan.size());
                        Toast toast = Toast.makeText(context, "Transaksi Dibatalkan", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });


                // Add button click listener for cancel action
            } else if ("fragmentbatal".equals(fragmentType)) {

                Log.d("namaruangan_batal", pemesanan_model.getNamaruangan());
                Log.d("hargaruangan_batal", String.valueOf(pemesanan_model.getHargaruangan()));
                Log.d("gambarproses_batal", pemesanan_model.getGambarruangan());
                Cvstatus.setBackgroundColor(context.getColor(R.color.black));
                Cvstatus.setRadius(200);
                statuspemesanan.setText("Transaksi Batal");
                btnaction.setText("Reorder");
                //transfer gambar ke holder
                int imageResource = context.getResources().getIdentifier(pemesanan_model.getGambarruangan(), "drawable", context.getPackageName());
                gambarpesanan.setImageResource(imageResource);
                btnaction.setBackgroundColor(Color.GREEN); // Change to your desired color
                // Add button click listener for reorder action
            }
        }
    }
}
