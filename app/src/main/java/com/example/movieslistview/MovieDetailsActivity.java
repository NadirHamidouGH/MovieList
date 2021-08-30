package com.example.movieslistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){

            Log.d("Err", "onCreate: "+e);
        }

        setContentView(R.layout.activity_movie_details);

        String title = getIntent().getExtras().getString("title");
        String description = getIntent().getExtras().getString("desciption");
        String date = getIntent().getExtras().getString("desciption");



    }
}