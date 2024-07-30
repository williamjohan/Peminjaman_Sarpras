package com.example.peminjaman_sarpras.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
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

import com.bumptech.glide.Glide;
import com.example.peminjaman_sarpras.API.ApiClient;
import com.example.peminjaman_sarpras.API.ApiService;
import com.example.peminjaman_sarpras.R;
import com.example.peminjaman_sarpras.model.Pemesanan_Model;
import com.example.peminjaman_sarpras.model.Ruangan_Model;
import com.example.peminjaman_sarpras.response.Pemesanan_Response;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PemesananAdapter extends RecyclerView.Adapter<PemesananAdapter.ViewHolder>{

    private List<Pemesanan_Model> listpemesanan = new ArrayList<>();
    private Context context;
    private DecimalFormat decimalformatter = new DecimalFormat("#,###,###");
    private String fragmentType;
    private ApiService apiService;

    public PemesananAdapter(List<Pemesanan_Model> listpemesanan, Context context, String fragmentType) {
        this.listpemesanan = listpemesanan;
        this.context = context;
        this.fragmentType = fragmentType;
        apiService = ApiClient.getClient().create(ApiService.class);
    }

    @NonNull
    @Override
    public PemesananAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listpemesanan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PemesananAdapter.ViewHolder holder, int position) {
        Pemesanan_Model pemesanan_model = listpemesanan.get(position);
        holder.bind(pemesanan_model);
    }

    @Override
    public int getItemCount() {
        return listpemesanan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namaitempesanan, hargaritempesanan, tanggalpesan, jamitempesanan, statuspemesanan;
        ImageView gambarpesanan;
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
            statuspemesanan.setText(pemesanan_model.getStatusPemesanan());

            // Initiate dan call get API ruangan
            Call<Ruangan_Model> call = apiService.getRuanganById(pemesanan_model.getIdRuangan());
            call.enqueue(new Callback<Ruangan_Model>() {
                @Override
                public void onResponse(Call<Ruangan_Model> call, Response<Ruangan_Model> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Ruangan_Model ruangan = response.body();
                        namaitempesanan.setText(ruangan.getNamaruangan());
                        hargaritempesanan.setText("Rp " + decimalformatter.format(ruangan.getHargaruangan()) + " /Jam");

                        // Glide untuk ambil gambar
                        Glide.with(context)
                                .load(ruangan.getGambar())
                                .into(gambarpesanan);

                    } else {
                        Log.e("PemesananAdapter", "Response error: " + response.code() + " - " + response.message());
                        Toast.makeText(context, "Ruangan tidak ditemukan", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Ruangan_Model> call, Throwable throwable) {
                    Log.e("PemesananAdapter", "API call failed", throwable);
                    Toast.makeText(context, "Gagal mengambil data ruangan", Toast.LENGTH_SHORT).show();
                }
            });

            if ("fragmentproses".equals(fragmentType)) {
                statuspemesanan.setText("Menunggu Pembayaran");
                btnaction.setText("Cancel");
                btnaction.setBackground(context.getResources().getDrawable(R.drawable.btn_warning_style_red));

                // Add button click listener for cancel action
                btnaction.setOnClickListener(view -> {
                    int position = getAdapterPosition();
                    Pemesanan_Model pemesananModel = listpemesanan.get(position);
                    int idPemesanan = pemesananModel.getIdPemesanan();

                    // Menampilkan ProgressDialog
                    ProgressDialog progressDialog = new ProgressDialog(context);
                    progressDialog.setMessage("Proses Pembatalan...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                    // Membuat objek Pemesanan_Model dengan status baru
                    Pemesanan_Model cancelModel = new Pemesanan_Model(idPemesanan, pemesananModel.getIdRuangan(), "cancel");

                    // Mengirim permintaan pembatalan ke server
                    apiService.cancelPemesanan(cancelModel).enqueue(new Callback<Pemesanan_Response>() {
                        @Override
                        public void onResponse(Call<Pemesanan_Response> call, Response<Pemesanan_Response> response) {
                            progressDialog.dismiss();

                            if (response.isSuccessful() && response.body() != null) {
                                if ("success".equals(response.body().getStatus())) {
                                    // Mengupdate UI dan model
                                    listpemesanan.remove(position);
                                    notifyItemRemoved(position);
                                    notifyItemRangeChanged(position, listpemesanan.size());

                                    // Menampilkan toast di thread utama
                                    ((Activity) context).runOnUiThread(() ->
                                            Toast.makeText(context, "Transaksi Dibatalkan", Toast.LENGTH_SHORT).show()
                                    );
                                } else {
                                    ((Activity) context).runOnUiThread(() ->
                                            Toast.makeText(context, "Gagal membatalkan pemesanan: " + response.body().getMessage(), Toast.LENGTH_SHORT).show()
                                    );
                                }
                            } else {
                                ((Activity) context).runOnUiThread(() ->
                                        Toast.makeText(context, "Respons tidak berhasil", Toast.LENGTH_SHORT).show()
                                );
                            }
                        }

                        @Override
                        public void onFailure(Call<Pemesanan_Response> call, Throwable t) {
                            ((Activity) context).runOnUiThread(() -> {
                                progressDialog.dismiss();
                                Toast.makeText(context, "Gagal menghubungi server: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                            });
                        }
                    });
                });
            }
        }
    }

    public void updateData(List<Pemesanan_Model> newData) {
        this.listpemesanan = newData;
        notifyDataSetChanged();
    }

}
