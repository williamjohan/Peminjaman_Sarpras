package com.example.peminjaman_sarpras.allpages;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.peminjaman_sarpras.MainActivity;
import com.example.peminjaman_sarpras.R;
import com.example.peminjaman_sarpras.adapter.CarouselAdapter;
import com.example.peminjaman_sarpras.constructor.GambarRuanganConstructor;
import com.example.peminjaman_sarpras.database.DBHelper;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

public class SubDetailContent extends AppCompatActivity {
    private TextView judulruangan,hargasewa;
    private ImageView imgback;
    private ViewPager2 viewPager;
    private CarouselAdapter gambaradapter;
    private TabLayout tabindicator;
    private Button btnpesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sub_detail_content);

        // Hide action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        //innit data
        imgback = findViewById(R.id.backicon);
        judulruangan = findViewById(R.id.TVheadercontent);
        viewPager = findViewById(R.id.viewpager);
        tabindicator = findViewById(R.id.tabindicator);
        btnpesan = findViewById(R.id.BTNpesan);
        hargasewa = findViewById(R.id.TVharga);

        //memasukkan data yang diterima dari intent ContentAdapter ke View layout
        Bundle extras = getIntent().getExtras();
        judulruangan.setText(extras.getString("nama_ruangan"));
        hargasewa.setText("Rp." + extras.getInt("harga_sewa")+ " / Jam");
        int idruangan = getIntent().getIntExtra("id_ruangan", 0);
        //bisa juga dibuat begini :
        //int idruangan = extras.getInt("id_ruangan");

        // Get the images for the room from the dbhelper
        DBHelper db = new DBHelper(this);
        // memanggil isi dari dbhelper lempar ke adapter
        List<GambarRuanganConstructor> listimages = db.getallresourcegambar(idruangan);

        // Check if list is empty and add default image if necessary
        if (listimages.isEmpty()) {
            listimages.add(new GambarRuanganConstructor(idruangan, 0, "default_image")); // Replace with your default image name
        }

        //memasukkan isi listcontent ke adapter
        gambaradapter = new CarouselAdapter(listimages, this);
        viewPager.setAdapter(gambaradapter);

        //setup tablayout into viewpager
//        tabindicator.setupWithViewPager(viewPager); --> tidak compatible dengan ViewPager2
        new TabLayoutMediator(tabindicator, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                // tidak perlu mengatur text untuk tab karena kita menggunakan drawable selector
            }
        }).attach();

        btnpesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: jalankan query sql untu memasukan data. kerjakan di Datainilitializer class

                // TODO: tampilkan dialog message dan pergi ke riwayat
                // Buat Intent untuk kembali ke MainActivity
                Intent intent = new Intent(SubDetailContent.this, MainActivity.class);
                // Sertakan informasi bahwa navigasi ke RiwayatFragment harus dilakukan
                intent.putExtra("navigateTo", "riwayat");
                Log.d("SubDetailContent", "onClick: " + intent);
                // Mulai activity MainActivity
                startActivity(intent);
                // Selesaikan activity saat ini
                finish();
            }
        });
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

///
//                NavController navController = Navigation.findNavController(SubDetailContent.this, R.id.nav_host_fragment_activity_main);
//                navController.navigate(R.id.navigation_riwayat);
//                finish();