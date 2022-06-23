package com.moringaschool.thewatchlist.fragments;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.thewatchlist.Constants;
import com.moringaschool.thewatchlist.R;
import com.moringaschool.thewatchlist.models.Result;
import com.moringaschool.thewatchlist.ui.ReviewsActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieReviewFragment extends Fragment implements View.OnClickListener{
    @BindView(R.id.movieName) TextView movieName;
    @BindView(R.id.releaseYear) TextView releaseYear;
    @BindView(R.id.director) TextView director;
    @BindView(R.id.summaryReview) TextView summaryReview;
    @BindView(R.id.movieImage) ImageView movieImage;
    @BindView(R.id.rating) TextView rating;
    @BindView(R.id.saveBtn)
    FloatingActionButton saveButton;
    @BindView(R.id.watchView) TextView mWatchTextView;
    @BindView(R.id.reviewsButton) Button mReview;

Result result;

FirebaseDatabase firebaseDatabase;

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
    if(result.getMultimedia()!=null) {
        Picasso.get().load(result.getMultimedia().getSrc()).into(movieImage);
    }
        saveButton.setOnClickListener(this);
    mWatchTextView.setOnClickListener(this);
    mReview.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

//        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//        FirebaseUser user = firebaseAuth.getCurrentUser();
//        String uid = user.getUid();

        if(v == saveButton){
            //Changes the heart image after click
            Drawable res = getResources().getDrawable(R.drawable.ic_baseline_favorite_24);

            saveButton.setImageDrawable(res);
            saveButton.setBackgroundColor(getResources().getColor(R.color.red));

            DatabaseReference movieRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_MOVIE).child("user");
            movieRef.push().setValue(result);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();

        }
        if(v == mWatchTextView){
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(result.getLink().getUrl()));

                startActivity(i);
        }
        if(v == mReview){

            Intent intent = new Intent(getActivity(), ReviewsActivity.class);
            intent.putExtra("movie", result); //serializable
            startActivity(intent);
        }


    }

}