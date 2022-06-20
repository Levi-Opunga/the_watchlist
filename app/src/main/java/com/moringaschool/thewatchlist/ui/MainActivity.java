package com.moringaschool.thewatchlist.ui;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.moringaschool.thewatchlist.Constants;
import com.moringaschool.thewatchlist.R;
import com.moringaschool.thewatchlist.models.Example;
import com.moringaschool.thewatchlist.models.Result;
import com.moringaschool.thewatchlist.networking.nycTimesApi;
import com.moringaschool.thewatchlist.networking.nycTimesClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<Result> results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = this.findViewById(R.id.button);
        button.setOnClickListener(v -> apiCall());

apiCall();

        apiCall();
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
                    Log.d("thisisit",results.toString());

                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }
}