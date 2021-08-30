package com.example.movieslistview

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class MovieDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
            Log.d("Err", "onCreate: $e")
        }
        setContentView(R.layout.activity_movie_details)
        val movieTitle: TextView
        val movieDescription: TextView
        val movieDate: TextView
        val movieNote: TextView
        val movieImg: ImageView
        movieTitle = findViewById(R.id.DetailsTitle)
        movieDate = findViewById(R.id.DetailsDate)
        movieDescription = findViewById(R.id.DetailsDescription)
        movieImg = findViewById(R.id.DetailsImage)
        movieNote = findViewById(R.id.DetailsNote)
        val title = intent.extras!!.getString("title")
        val date = "Initial release " + intent.extras!!.getString("date")
        val description = intent.extras!!.getString("desciption")
        val note = "IMDb " + intent.extras!!.getString("note") + "/10"
        val imgUrl = intent.extras!!.getString("image")
        Picasso.get().load(imgUrl).into(movieImg)
        movieTitle.text = title
        movieNote.text = note
        movieDate.text = date
        movieDescription.text = description
    }
}