package com.moringaschool.thewatchlist.fragments;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.thewatchlist.Constants;
import com.moringaschool.thewatchlist.R;
import com.moringaschool.thewatchlist.adapters.MultipleReviewAdapter;
import com.moringaschool.thewatchlist.models.Result;
import com.moringaschool.thewatchlist.models.Reviews;
import com.moringaschool.thewatchlist.ui.ReviewsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieReviewFragment extends Fragment implements View.OnClickListener{
    @BindView(R.id.itsName) TextView movieName;
    @BindView(R.id.releaseYear) TextView releaseYear;
    @BindView(R.id.director) TextView director;
    @BindView(R.id.summaryReview) TextView summaryReview;
    @BindView(R.id.movieImage) ImageView movieImage;
    @BindView(R.id.rating) TextView rating;
    @BindView(R.id.saveBtn)
    FloatingActionButton saveButton;
    @BindView(R.id.watchView) TextView mWatchTextView;
    @BindView(R.id.reviewsButton) Button mReview;
    List<Reviews> list = new ArrayList<Reviews>();
Result result;

FirebaseDatabase firebaseDatabase;
    private DatabaseReference movieRef;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

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

        for (int i = 0; i < 1; i++) {
            movieRef = FirebaseDatabase.getInstance().getReference("Reviews");


            movieRef.addValueEventListener(new ValueEventListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Reviews review= dataSnapshot.getValue(Reviews.class);
                        list.add(review);

                        Log.d("ssssshhh","sssshhh");
                    }

                    if(list != null){
                        list =  list.stream().filter(rev -> rev.getMovie()
                                        .equals(result.getDisplayTitle()))
                                .collect(Collectors.toList());
                    }

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    mRecyclerView.setLayoutManager(linearLayoutManager);
                    MultipleReviewAdapter adapter = new MultipleReviewAdapter(list,getContext());
                    mRecyclerView.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



        }
    director.setText(result.getByline());
    summaryReview.setText(result.getSummaryShort());
    movieName.setText(result.getDisplayTitle());
    if(result.getOpeningDate()==null){
        result.setOpeningDate("Unavailable");
    }
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
FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            DatabaseReference movieRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_MOVIE).child(user.getDisplayName());
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