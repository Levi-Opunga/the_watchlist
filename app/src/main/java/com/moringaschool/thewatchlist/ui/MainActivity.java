package com.moringaschool.thewatchlist.ui;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SearchView;

import com.moringaschool.thewatchlist.Constants;
import com.moringaschool.thewatchlist.R;
import com.moringaschool.thewatchlist.adapters.MovieItemAdapter;
import com.moringaschool.thewatchlist.models.Example;
import com.moringaschool.thewatchlist.models.Result;
import com.moringaschool.thewatchlist.networking.nycTimesApi;
import com.moringaschool.thewatchlist.networking.nycTimesClient;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Button button = this.findViewById(R.id.button);
        apiCall();
        button.setOnClickListener(v -> apiCall());
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