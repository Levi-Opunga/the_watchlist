package com.moringaschool.thewatchlist.adapters;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.moringaschool.thewatchlist.fragments.MovieReviewFragment;
import com.moringaschool.thewatchlist.models.Result;

import java.util.ArrayList;
import java.util.List;

public class MoviePagerAdapter extends FragmentStateAdapter {
    List<Result> list;
    public static int position;
    boolean first= true;

    public MoviePagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle,  List<Result> list) {
        super(fragmentManager, lifecycle);
        this.list = list;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public Fragment createFragment(int position) {
      List<Fragment> fragments = new ArrayList<Fragment>();
      list.forEach(result ->{
          fragments.add(new MovieReviewFragment(result));
      });


        if(first){
            first = false;
            return fragments.get(MoviePagerAdapter.position);
        }

        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
