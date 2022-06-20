package com.moringaschool.thewatchlist.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moringaschool.thewatchlist.R;
import com.moringaschool.thewatchlist.models.Result;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MovieReviewFragment extends Fragment {
    @BindView(R.id.movieName)
    TextView movieName;
    @BindView(R.id.releaseYear)
    TextView releaseYear;
    @BindView(R.id.director)
    TextView director;
    @BindView(R.id.summaryReview)
    TextView summaryReview;
    @BindView(R.id.movieImage)
    ImageView movieImage;
    @BindView(R.id.rating)
            TextView rating;


Result result;

    public MovieReviewFragment(Result result) {
        this.result = result;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_review, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this,view);
    director.setText(result.getByline());
    summaryReview.setText(result.getSummaryShort());
    movieName.setText(result.getDisplayTitle());
    releaseYear.setText(result.getOpeningDate());
    rating.setText(result.getMpaaRating());
        Picasso.get().load(result.getMultimedia().getSrc()).into(movieImage);

    }
}