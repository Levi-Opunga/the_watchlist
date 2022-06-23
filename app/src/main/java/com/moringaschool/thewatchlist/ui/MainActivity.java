package com.moringaschool.thewatchlist.ui;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import com.google.android.material.tabs.TabLayout;
import com.moringaschool.thewatchlist.Constants;
import com.moringaschool.thewatchlist.R;
import com.moringaschool.thewatchlist.adapters.MovieItemAdapter;
import com.moringaschool.thewatchlist.models.Example;
import com.moringaschool.thewatchlist.models.Result;
import com.moringaschool.thewatchlist.networking.nycTimesApi;
import com.moringaschool.thewatchlist.networking.nycTimesClient;
import com.moringaschool.thewatchlist.adapters.ImageAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<Result> results;
    @BindView(R.id.viewPager)
     ViewPager mViewPager;
    @BindView(R.id.left)
    ImageView left;
    @BindView(R.id.right)
    ImageView right;
    private ImageAdapter adapterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Constants.saved = false;
        mViewPager.setVisibility(View.GONE);
        right.setVisibility(View.GONE);
        left.setVisibility(View.GONE);

        TabLayout tablayout = this.findViewById(R.id.tabLayout);

        tablayout.selectTab(tablayout.getTabAt(0));
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    return;
                }
                if (tab.getPosition() == 2) {
                    Intent intent = new Intent(MainActivity.this, SavedMoviesActivity.class);
                    startActivity(intent);
                }

                if (tab.getPosition() == 1) {
                    Intent intent = new Intent(MainActivity.this,UserReviewsActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        apiCall();


    }

    private void apiCall() {
        nycTimesApi api = nycTimesClient.getClient();
        Call<Example> call = api.getSomeMovies(Constants.API_KEY);
        call.enqueue(new Callback<Example>() {

            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                if (response.isSuccessful()) {

                    results = response.body().getResults();
                    Constants.RESULTS_RESTORE = results;
                    Constants.RESULTS = results;
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(linearLayoutManager);
                    MovieItemAdapter adapter = new MovieItemAdapter(results, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                    adapterView = new ImageAdapter(getApplicationContext(),results);
                    mViewPager.setAdapter(adapterView);



                    Log.d("thisisit", results.toString());

                }


            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        ButterKnife.bind(this);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query == null){
                    return false;
                }
                mViewPager.setVisibility(View.GONE);
                right.setVisibility(View.GONE);
                left.setVisibility(View.GONE);

                nycTimesApi api = nycTimesClient.getClient();
                Call<Example> call = api.searchMovies(query, Constants.API_KEY);
                call.enqueue(new Callback<Example>() {

                    @Override
                    public void onResponse(Call<Example> call, Response<Example> response) {

                        if (response.isSuccessful()) {

                            results = response.body().getResults();
                            if (results == null){
                                searchView.setQuery("",true);
                                searchView.setQueryHint("Not Found enter another search");
                                return;
                            }
                            Constants.RESULTS = results;
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(linearLayoutManager);
                            MovieItemAdapter adapter = new MovieItemAdapter(results, getApplicationContext());
                            recyclerView.setAdapter(adapter);




                        }
                    }

                    @Override
                    public void onFailure(Call<Example> call, Throwable t) {

                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText== null){
                    return false;
                }
                if(newText.equals("  ") || newText.equals("   ") || newText.equals("    ")){
                    searchView.setQuery("",true);
                    searchView.setQueryHint("Enter Valid entry");

                    return false;
                }

//                nycTimesApi api = nycTimesClient.getClient();
//                Call<Example> call = api.searchMovies(newText, Constants.API_KEY);
//                call.enqueue(new Callback<Example>() {
//
//                    @Override
//                    public void onResponse(Call<Example> call, Response<Example> response) {
//
//                        if (response.isSuccessful()) {
//
//                            results = response.body().getResults();
//                            Constants.RESULTS = results;
//                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
//                            recyclerView.setLayoutManager(linearLayoutManager);
//                            MovieItemAdapter adapter = new MovieItemAdapter(results, getApplicationContext());
//                            recyclerView.setAdapter(adapter);
//
//
//
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Example> call, Throwable t) {
//
//                    }
//                });
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }
}