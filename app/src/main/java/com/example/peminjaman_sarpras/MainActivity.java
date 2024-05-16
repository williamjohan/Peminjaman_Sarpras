package com.example.peminjaman_sarpras;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.peminjaman_sarpras.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //hide action bar
        getSupportActionBar().hide();

        //Memasukan data ke DBhelper
        DBHelper db = new  DBHelper(MainActivity.this);
//        db.dml("INSERT INTO list_peminjaman (nama_list,jumlah_venue,resgambar)" +
//                "VALUES('Ruang Rapat','Ada 2 Venue','contentruangrapat')"
//        );
//
//        db.dml("INSERT INTO list_peminjaman (nama_list,jumlah_venue,resgambar)" +
//                "VALUES('Co-Working Space','Ada 5 Venue','contentcoworkingspace')"
//        );
//        db.dml("INSERT INTO list_peminjaman (nama_list,jumlah_venue,resgambar)" +
//                "VALUES('Aula Hall Room','Ada 3 Venue','contentaulahallroom')"
//        );


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //mengatur flow dari icon navbar
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_beranda, R.id.navigation_riwayat, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);



    }

}