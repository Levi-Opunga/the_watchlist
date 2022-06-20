package com.moringaschool.thewatchlist.networking;

import com.google.gson.Gson;
import com.moringaschool.thewatchlist.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class nycTimesClient {
    public static Retrofit retrofit = null;

    public static nycTimesApi getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

return retrofit.create(nycTimesApi.class);
    }
}
