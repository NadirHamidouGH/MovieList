package com.example.movieslistview

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import java.util.*

///
class MainActivity : AppCompatActivity() {
    var mvvm = MovieViewModel()
    var recyclerView: RecyclerView? = null
    var movies: List<movie>? = null
    var adapter: MovieAdapter? = null
    private val RequestQueue: Any? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
            Log.d("Err", "onCreate: $e")
        }
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.listMoviesRecyclerView)



        try {
            mvvm.extractMovies(applicationContext, recyclerView)
        } catch (e: Exception) {
            Log.d("Extraction movies  ", "Error : $e")
        }
    }

    companion object {
        private const val JSON_URL = "https://api.themoviedb.org/3/discover/movie?api_key=08fce566e3d5a9aa500c2ccf5c32eb94"
    }
}