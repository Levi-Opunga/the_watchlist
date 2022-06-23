package com.moringaschool.thewatchlist.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.moringaschool.thewatchlist.R;
import com.moringaschool.thewatchlist.fragments.OnBoardingFragment1;
import com.moringaschool.thewatchlist.fragments.OnBoardingFragment2;
import com.moringaschool.thewatchlist.fragments.OnBoardingFragment3;

public class SplashScreenActivity extends AppCompatActivity {
    LottieAnimationView lottieAnimationView;
    TextView appName;
    ImageView splashImg;

    private static final int NUM_PAGES =3;
    private ViewPager viewPager;
    private ScreenSlidePageAdapter pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        lottieAnimationView = findViewById(R.id.lottie);
        appName = findViewById(R.id.app_name);
        splashImg = findViewById(R.id.background);

        viewPager = findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);


        splashImg.animate().translationY(-4000).setDuration(1000).setStartDelay(4000);
        appName.animate().translationY(4000).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(1400).setDuration(1000).setStartDelay(4000);

    }

    private class ScreenSlidePageAdapter extends FragmentStatePagerAdapter {

        public ScreenSlidePageAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
         switch (position){
             case 0:
                 OnBoardingFragment1 tab1 = new OnBoardingFragment1();
                 return tab1;
             case 1:
                 OnBoardingFragment2 tab2 = new OnBoardingFragment2();
                 return tab2;
             case 2:
                 OnBoardingFragment3 tab3 = new OnBoardingFragment3();
                 return tab3;
         }
         return null;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}