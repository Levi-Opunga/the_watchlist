package com.moringaschool.thewatchlist;

import com.moringaschool.thewatchlist.models.Result;

import java.util.ArrayList;
import java.util.List;

public class Constants {
    public static final String BASE_URL = "https://api.nytimes.com/svc/movies/v2/";
    public static  final String API_KEY = "dF8y2v26G2ArfgK2Ji7ZJI9YnXn9uSZy";
    public static List<Result> RESULTS = new ArrayList<Result>();
    public static List<Result> RESULTS_SAVE = new ArrayList<Result>();

    public static final String FIREBASE_CHILD_MOVIE = "MOVIE";
    public static final String FIREBASE_CHILD_SEARCHED_MOVIES = "searchedMovies";
    public static List<Result> RESULTS_RESTORE;
    public static boolean saved;
}
