package com.example.peminjaman_sarpras;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.peminjaman_sarpras.database.DBHelper;
import com.example.peminjaman_sarpras.database.DataInitializer;
import com.example.peminjaman_sarpras.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public DBHelper db;
    boolean check = false;
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String KEY_DB_INIT = "db_init";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean hasDbInitRun = settings.getBoolean(KEY_DB_INIT, false);

        //hide action bar

        db = new  DBHelper(MainActivity.this);
        DataInitializer startdata = new DataInitializer(db);

        // Check if db.init() has been run before
        if (!hasDbInitRun) {
            // Run dataInitializer.init() for the first time
            startdata.initDB();

            // Save the state that db.init() has been run
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean(KEY_DB_INIT, true);
            editor.apply();
        }

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //Memasukan data ke DBhelper

//        db.dropTable_ruangan();
//        db.dropTable_listpeminjaman();




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