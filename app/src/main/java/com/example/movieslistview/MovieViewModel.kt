package com.example.movieslistview

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import java.util.*

class MovieViewModel : ViewModel() {
    var moviesL: MutableList<movie> = ArrayList()
    var adapter: MovieAdapter? = null
    private val RequestQueue: Any? = null
    fun extractMovies(ctx: Context?, recyclerView: RecyclerView) {
        val queue = Volley.newRequestQueue(ctx)
        val jsonArrayRequest = JsonObjectRequest(Request.Method.GET, JSON_URL, null, Response.Listener { response ->
            var movieArray: JSONArray? = null
            try {
                movieArray = response.getJSONArray("results")
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            var i : Int = 0;
            var ListSize : Int = 0;
            ListSize = movieArray?.length()!!;
            for (i in 0..ListSize) {
                val movie = movie()
                try {
                    val movieObject = movieArray!!.getJSONObject(i)
                    movie.id = movieObject.getInt("id")
                    movie.title = movieObject.getString("title")
                    movie.note = movieObject.getString("vote_average")
                    movie.date = movieObject.getString("release_date")
                    movie.description = movieObject.getString("overview")
                    movie.imgUrl = "https://image.tmdb.org/t/p/w500" + movieObject.getString("poster_path")
                    Log.d("err", "onResponse: " + movie.title)
                    moviesL.add(movie)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
            recyclerView.layoutManager = LinearLayoutManager(ctx)
            adapter = MovieAdapter(ctx, moviesL)
            recyclerView.adapter = adapter
        }
                , Response.ErrorListener { error ->
            Log.d("tag", "onErrorResponse: " + error.message)
            Toast.makeText(ctx, "Error :" + error.message, Toast.LENGTH_SHORT).show()
        })
        queue.add(jsonArrayRequest)
    }

    companion object {
        private const val JSON_URL = "https://api.themoviedb.org/3/discover/movie?api_key=08fce566e3d5a9aa500c2ccf5c32eb94"
    }
}