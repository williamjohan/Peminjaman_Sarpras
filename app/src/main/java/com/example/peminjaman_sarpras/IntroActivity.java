package com.example.peminjaman_sarpras;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.peminjaman_sarpras.adapter.IntroViewPageAdapter;
import com.example.peminjaman_sarpras.model.IntroView_Model;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {
    private ViewPager screenpager;
    IntroViewPageAdapter introViewPageAdapter;
    TabLayout tabIndicator;
    Button btnNext;
    Button btnGetStarted;
    int position = 0;
    Animation btnAnim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        //make the activity on full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_intro);

        //Checking when this app about to launch whether is already opened before or not
        if (restorePrefData()) {
            Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(mainActivity);
            finish();
        }
        //hide action bar in activity
        getSupportActionBar().hide();


        //Inisialisasi views
        btnNext = findViewById(R.id.btnnext);
        btnGetStarted = findViewById(R.id.btnGStart);
        tabIndicator = findViewById(R.id.tab_indicator);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_anim);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //buat list screennya
        List<IntroView_Model> mList = new ArrayList<>();
        mList.add(new IntroView_Model("Meeting Room", "In a laoreet purus. Integer turpis quam, laoreet id orci nec, ultrices lacinia nunc. Aliquam erat vo", R.drawable.rapat_introscreen));
        mList.add(new IntroView_Model("Coworking-Space", "In a laoreet purus. Integer turpis quam, laoreet id orci nec, ultrices lacinia nunc. Aliquam erat vo", R.drawable.coworkingspace_introscreen));
        mList.add(new IntroView_Model("Aula Hallroom", "In a laoreet purus. Integer turpis quam, laoreet id orci nec, ultrices lacinia nunc. Aliquam erat vo", R.drawable.aula_introscreen));

/* Ngecek masuk ke listnya
         for (IntroView_Model item : mList) {
             Log.d("IntroView_Model", "Title: " + item.getTitle() + ", Description: " + item.getDescription() + ", Image: " + item.getScreenImg());
         }
 */


        //setup viewpager
        screenpager = findViewById(R.id.screen_pager);
        introViewPageAdapter = new IntroViewPageAdapter(this, mList);
        screenpager.setAdapter(introViewPageAdapter);

        //setup tablayout into viewpager
        tabIndicator.setupWithViewPager(screenpager);

        //Next button click listener
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = screenpager.getCurrentItem();
                if (position < mList.size()) {
                    position++;
                    screenpager.setCurrentItem(position);
//                    Log.d("IntroView_Model", "Title: " + mList.get(position).getTitle() + ", Description: " + mList.get(position).getDescription() + ", Image: " + mList.get(position).getScreenImg());
                }
                if (position == mList.size() - 1) { // ketika sampai di page terakhir atau screen terakhir.
                    //TODO : show Getstarted button and hide next button dan indicator tablayout.
                    loadlastscreen();
                }
            }
        });

        //btn Getstarted onclick listener
        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start main activity
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                //memastikan user tidak bisa kembali ke halaman ini
                //artinya hanya sekali saat menginstal aplikasi kehalaman ini.

                savePreferedData();
                finish();
            }
        });

        //mengatur tablayout dengan swipe
        screenpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //nothing
            }

            @Override
            public void onPageSelected(int position) {
                if (position == tabIndicator.getTabCount() - 1) {
                    tabIndicator.setVisibility(View.GONE);
                    btnNext.setVisibility(View.GONE);
                    btnGetStarted.setVisibility(View.VISIBLE);
                    btnGetStarted.setAnimation(btnAnim);
                } else {
                    tabIndicator.setVisibility(View.VISIBLE);
                    btnNext.setVisibility(View.VISIBLE);
                    btnGetStarted.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //do nothing
            }
        });


    }

    private boolean restorePrefData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myprefs", MODE_PRIVATE);
        Boolean isIntroActivityOpenedBefore = pref.getBoolean("isFirstTime", false);
        return isIntroActivityOpenedBefore;
    }

    private void savePreferedData() {
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("myprefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("isFirstTime", true);
        editor.apply();
    }


    //show button getstarted button and hide next button and indicator tablayout
    private void loadlastscreen() {
        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);
        //TODO : add animation to getstarted button

        //Set up animation
        btnGetStarted.setAnimation(btnAnim);
    }

}

