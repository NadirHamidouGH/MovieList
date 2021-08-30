package com.example.movieslistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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

        TextView movieTitle,movieDescription,movieDate,movieNote;
        ImageView movieImg;


        movieTitle = findViewById(R.id.DetailsTitle);
        movieDate = findViewById(R.id.DetailsDate);
        movieDescription =  findViewById(R.id.DetailsDescription );
        movieImg =  findViewById(R.id.DetailsImage);
        movieNote =  findViewById(R.id.DetailsNote);

        String title = getIntent().getExtras().getString("title");
        String date = "Initial release "+ getIntent().getExtras().getString("date");
        String description = getIntent().getExtras().getString("desciption");
        String note = "IMDb "+ getIntent().getExtras().getString("note") + "/10";
        String imgUrl =  getIntent().getExtras().getString("image");

        Picasso.get().load(imgUrl).into(movieImg);

        movieTitle.setText(title);
        movieNote.setText(note);
        movieDate.setText(date);
        movieDescription.setText(description);




    }
}