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
import com.moringaschool.thewatchlist.adapters.ReviewItemAdapter;
import com.moringaschool.thewatchlist.models.Result;
import com.moringaschool.thewatchlist.models.Reviews;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserReviewsActivity extends AppCompatActivity {

    private DatabaseReference movieRef;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private List<Reviews> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_reviews);
        ButterKnife.bind(this);
        TabLayout tablayout = this.findViewById(R.id.tabLayout);
        tablayout.selectTab(tablayout.getTabAt(1));
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    Intent intent = new Intent(UserReviewsActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                if (tab.getPosition() == 2) {
                    Intent intent = new Intent(UserReviewsActivity.this, SavedMoviesActivity.class);
                    startActivity(intent);
                }

                if (tab.getPosition() == 1) {

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        for (int i = 0; i < 1; i++) {
            movieRef = FirebaseDatabase.getInstance().getReference("Reviews");


            movieRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Reviews result = dataSnapshot.getValue(Reviews.class);
                        list.add(result);
                        Log.d("ssssshhh","sssshhh");
                    }
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    mRecyclerView.setLayoutManager(linearLayoutManager);
                    ReviewItemAdapter adapter = new ReviewItemAdapter(list, getApplication().getApplicationContext());
                    mRecyclerView.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



        }
    }
}