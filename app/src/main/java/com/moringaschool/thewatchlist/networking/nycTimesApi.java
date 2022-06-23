package com.moringaschool.thewatchlist.networking;

import com.moringaschool.thewatchlist.models.Example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface nycTimesApi {
    @GET("reviews/all.json")
    Call<Example> getSomeMovies(
            @Query("api-key") String api_key
    );

    @GET("reviews/search.json")
    Call<Example> searchMovies(
            @Query("query") String query,
            @Query("api-key") String api_key
    );


}
