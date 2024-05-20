package com.example.peminjaman_sarpras;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.window.OnBackInvokedDispatcher;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.peminjaman_sarpras.database.DBHelper;
import com.example.peminjaman_sarpras.database.DataInitializer;
import com.example.peminjaman_sarpras.databinding.ActivityMainBinding;
import com.example.peminjaman_sarpras.pages.beranda.BerandaFragment;
import com.example.peminjaman_sarpras.pages.profile.ProfileFragment;
import com.example.peminjaman_sarpras.pages.riwayat.RiwayatFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public DBHelper db;
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String KEY_DB_INIT = "db_init";
    private static final String SELECTED_ITEM = "selected_item";
    private int selectedItem;

    private BottomNavigationView bottomNavigationView;
    private int currentFragmentId;
    private Window window;
    private WindowInsetsController controller;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //init var
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        db = new DBHelper(MainActivity.this);
        DataInitializer startdata = new DataInitializer(db);

        // Check if db.init() has been run before
        boolean hasDbInitRun = settings.getBoolean(KEY_DB_INIT, false);
        if (!hasDbInitRun) {
            // Run dataInitializer.init() for the first time
            startdata.initDB();

            // Save the state that db.init() has been run
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean(KEY_DB_INIT, true);
            editor.apply();
        }
        //hide actionbar
        getSupportActionBar().hide();
        //make the activity on full screen




        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        bottomNavigationView = findViewById(R.id.bottom_nav_view);

        boolean hasNavigatedFromIntent = false;


        // mengatur intent dahulu apakah ada yang masuk
        if (getIntent() != null && getIntent().hasExtra("navigateTo")) {
            Fragment selectedFragment = null;
            String destination = getIntent().getStringExtra("navigateTo");
            int idRuangan = getIntent().getIntExtra("idRuangan", -1);
            hasNavigatedFromIntent = true;

            if (destination != null && destination.equals("riwayat")) {
                selectedFragment = new RiwayatFragment();
                //mengirim id ruangan ke riwayat
                Bundle args = new Bundle();

                Log.d("di main activity", "ini nilainya" + String.valueOf(idRuangan));

                args.putInt("idRuangan", idRuangan);
                selectedFragment.setArguments(args);

                bottomNavigationView.setSelectedItemId(R.id.navigation_riwayat);

            } else if(destination != null && destination.equals("profile")) {
                selectedFragment = new ProfileFragment();
                bottomNavigationView.setSelectedItemId(R.id.navigation_profile);
            } else if(destination != null && destination.equals("beranda")) {
                selectedFragment = new BerandaFragment();
                bottomNavigationView.setSelectedItemId(R.id.navigation_beranda);
            }

            //kerjakan dari yang ditampung di intent
            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_activity_main, selectedFragment).commit();
            }
        }

        // Periksa savedInstanceState hanya jika tidak bernavigasi dari intent
        if (!hasNavigatedFromIntent) {
            if (savedInstanceState != null) {
                selectedItem = savedInstanceState.getInt(SELECTED_ITEM, 0);
                MenuItem selectedMenuItem = bottomNavigationView.getMenu().findItem(selectedItem);
                selectedMenuItem.setChecked(true);
                currentFragmentId = selectedItem;
            } else {
                // Set default selected item
                bottomNavigationView.setSelectedItemId(R.id.navigation_beranda);
                currentFragmentId = R.id.navigation_beranda;
            }
        }


        bottomNavigationView.setOnItemSelectedListener(
                new NavigationBarView.OnItemSelectedListener(){
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        Fragment selectedFragment = null;
                        int itemId = menuItem.getItemId();
                        if (itemId == R.id.navigation_beranda) {
                            selectedFragment = new BerandaFragment();
                            selectedItem = R.id.navigation_beranda;
                            setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.getstarted));

                        } else if (itemId == R.id.navigation_riwayat) {
                            selectedFragment = new RiwayatFragment();
                            selectedItem = R.id.navigation_riwayat;
                            setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.getstarted));


                        } else if (itemId == R.id.navigation_profile) {
                            selectedFragment = new ProfileFragment();
                            selectedItem = R.id.navigation_profile;
                            setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.white));
                            setStatusBarTextDark(true);

                        }
                        currentFragmentId = itemId;
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_activity_main,
                                selectedFragment).commit();
                        return true;
                    }
                });

        // Handle back press
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (currentFragmentId != R.id.navigation_beranda) {
                    bottomNavigationView.setSelectedItemId(R.id.navigation_beranda);
                    currentFragmentId = R.id.navigation_beranda;
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_activity_main, new BerandaFragment()).commit();
                } else {
                    finish();
                }
            }
        });

    }


    @NonNull
    @Override
    public OnBackInvokedDispatcher getOnBackInvokedDispatcher() {
        return super.getOnBackInvokedDispatcher();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECTED_ITEM, selectedItem);
    }

    public void setStatusBarColor(int color) {
        window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(color);
    }

    public void setStatusBarTextDark(boolean dark) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            controller = getWindow().getInsetsController();
            if (controller != null) {
                if (dark) {
                    controller.setSystemBarsAppearance(
                            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                    );
                } else {
                    controller.setSystemBarsAppearance(0, WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS);
                }
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            if (dark) {
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                decor.setSystemUiVisibility(0);
            }
        }
    }

}