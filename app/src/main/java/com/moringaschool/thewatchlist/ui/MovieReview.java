package com.moringaschool.thewatchlist.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.moringaschool.thewatchlist.Constants;
import com.moringaschool.thewatchlist.R;
import com.moringaschool.thewatchlist.adapters.MoviePagerAdapter;
import com.moringaschool.thewatchlist.models.Result;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieReview extends AppCompatActivity {
@BindView(R.id.viewpager)
    ViewPager2 viewpager;
    List<Result> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_review);
        ButterKnife.bind(this);
        list = Constants.RESULTS;
        FragmentManager  fragmentManager = getSupportFragmentManager();


        viewpager.setAdapter(new MoviePagerAdapter(fragmentManager,getLifecycle(),list));


    }
}