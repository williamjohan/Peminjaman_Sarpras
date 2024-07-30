    package com.example.peminjaman_sarpras.adapter;

    import android.content.Context;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ImageView;

    import androidx.annotation.NonNull;
    import androidx.recyclerview.widget.RecyclerView;

    import com.bumptech.glide.Glide;
    import com.example.peminjaman_sarpras.R;
    import com.example.peminjaman_sarpras.model.DetailGambarRuangan_Model;

    import java.util.List;

    public class CarouselAdapter extends RecyclerView.Adapter<CarouselAdapter.ViewHolder> {

        private List<DetailGambarRuangan_Model> listimageresource;
        private Context context;

        public CarouselAdapter(List<DetailGambarRuangan_Model> imageresource, Context context) {
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
            DetailGambarRuangan_Model image = listimageresource.get(position);

            String imageUrl = image.getResgambar();

            // Memuat gambar menggunakan Glide
            Glide.with(context)
                    .load(imageUrl)
                    .centerCrop()
                    .centerCrop()
                    .placeholder(R.drawable.nofound404)  // Gambar default jika gambar belum dimuat
                    .error(R.drawable.nofound404)  // Gambar yang muncul jika terjadi error
                    .into(holder.viewimage);

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
