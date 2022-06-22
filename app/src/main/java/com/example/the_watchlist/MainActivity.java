package com.example.the_watchlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.hello);
        FirebaseUser user;
        FirebaseAuth auth = FirebaseAuth.getInstance();
        user =auth.getCurrentUser();
        textView.setText(user.getDisplayName());
    }
}