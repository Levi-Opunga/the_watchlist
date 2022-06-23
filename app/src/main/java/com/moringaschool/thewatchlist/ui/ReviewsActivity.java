package com.moringaschool.thewatchlist.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.thewatchlist.R;
import com.moringaschool.thewatchlist.models.Result;
import com.moringaschool.thewatchlist.models.Reviews;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewsActivity extends AppCompatActivity {
    @BindView(R.id.reviewText) TextInputEditText mText;
    @BindView(R.id.ratingBar) RatingBar mRatingBar;
    @BindView(R.id.submitReview) Button mSubmitBtn;
    @BindView(R.id.movieName) TextView mMovieName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        ButterKnife.bind(this);


        Result result = (Result) getIntent().getSerializableExtra("movie"); //get serializable
        mMovieName.setText(result.getDisplayTitle()); //Display the movie name


        //getting the rating
        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String currentUser = user.getDisplayName(); //gets the user name
                String review = mText.getText().toString();
                Float rating = mRatingBar.getRating();


                Reviews reviews = new Reviews(review, rating, currentUser);
                DatabaseReference reviewRef = FirebaseDatabase.getInstance().getReference("Reviews");
                reviewRef.push().setValue(reviews);

                Intent intent = new Intent(ReviewsActivity.this,UserReviewsActivity.class);
                startActivity(intent);
            }
        });
    }
}