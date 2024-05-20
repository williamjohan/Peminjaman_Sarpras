package com.example.peminjaman_sarpras;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashScreen extends AppCompatActivity {
    ImageView logo;
    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        logo = findViewById(R.id.image_logo);
        animation = AnimationUtils.loadAnimation(this, R.anim.anim_splash);

        logo.startAnimation(animation);

        //start introactivity setelah delay splash
        new Handler(getMainLooper()).postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, IntroActivity.class);
                startActivity(intent);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                finish();
            }
        },2000);
    }
}