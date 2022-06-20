package com.moringaschool.thewatchlist.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.moringaschool.thewatchlist.R;

public class SplashScreenActivity extends AppCompatActivity {
    LottieAnimationView lottieAnimationView;
    TextView appName;
    ImageView splashImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        lottieAnimationView = findViewById(R.id.lottie);
        appName = findViewById(R.id.app_name);
        splashImg = findViewById(R.id.background);

        splashImg.animate().translationY(-2500).setDuration(1000).setStartDelay(4000);
        appName.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(1400).setDuration(1000).setStartDelay(4000);


    }
}