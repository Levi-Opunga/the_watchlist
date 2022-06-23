package com.moringaschool.thewatchlist.adapters;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.collection.CircularArray;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.moringaschool.thewatchlist.Constants;
import com.moringaschool.thewatchlist.R;
import com.moringaschool.thewatchlist.models.Result;
import com.moringaschool.thewatchlist.ui.MovieReview;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends PagerAdapter {
    Context mContext;
    private List<Result> list = new ArrayList<>();

    public ImageAdapter(Context context,List<Result> list) {
        this.mContext = context;
        this.list = list;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    private int[] sliderImageId = new int[]{
            R.drawable.apple, R.drawable.google, R.drawable.button_bg,R.drawable.popcorn, R.drawable.twitter,
    };

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Picasso.get().load(list.get(position).getMultimedia().getSrc()).into(imageView);
        imageView.setOnClickListener(v -> {
            MoviePagerAdapter.position = position;
            Intent intent = new Intent(mContext, MovieReview.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        });
      //  imageView.setImageResource(sliderImageId[position]);
        ((ViewPager) container).addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
