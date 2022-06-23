package com.moringaschool.thewatchlist.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.thewatchlist.Constants;
import com.moringaschool.thewatchlist.R;
import com.moringaschool.thewatchlist.adapters.MovieItemAdapter;
import com.moringaschool.thewatchlist.models.Result;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedMoviesActivity extends AppCompatActivity {

    private DatabaseReference movieRef;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private List<Result> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_movies);
        ButterKnife.bind(this);
        Constants.saved = true;

        TabLayout tablayout = this.findViewById(R.id.tabLayout);
        tablayout.selectTab(tablayout.getTabAt(2));
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    Intent intent = new Intent(SavedMoviesActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                if (tab.getPosition() == 2) {
                    return;
                }
                if (tab.getPosition() == 1) {
                    Intent intent = new Intent(SavedMoviesActivity.this, UserReviewsActivity.class);
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


//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        String uid = user.getUid();
        for (int i = 0; i < 2; i++) {
            movieRef = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_MOVIE).child("user");


            movieRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Result result = dataSnapshot.getValue(Result.class);
                        list.add(result);
                    }
                    Constants.RESULTS_SAVE = list;
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(linearLayoutManager);
                    MovieItemAdapter adapter = new MovieItemAdapter(list, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }
    }

}

