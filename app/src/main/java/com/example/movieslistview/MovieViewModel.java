package com.example.movieslistview;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MovieViewModel extends ViewModel {

    private static String JSON_URL = "https://api.themoviedb.org/3/discover/movie?api_key=08fce566e3d5a9aa500c2ccf5c32eb94";

    List<movie> moviesL = new ArrayList<>();

    MovieAdapter adapter;
    private Object RequestQueue;

    public void extractMovies(final Context ctx, final RecyclerView recyclerView) {

        RequestQueue queue = Volley.newRequestQueue(ctx);
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                JSONArray movieArray=null;

                try {
                    movieArray  = response.getJSONArray("results");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < movieArray.length(); i++) {


                    movie movie = new movie();

                    try {
                        JSONObject movieObject = movieArray.getJSONObject(i);
                        movie.setId(movieObject.getInt("id"));
                        movie.setTitle(movieObject.getString("title"));
                        movie.setNote(movieObject.getString("vote_average"));
                        movie.setDate(movieObject.getString("release_date"));
                        movie.setDescription(movieObject.getString("overview"));
                        movie.setImgUrl("https://image.tmdb.org/t/p/w500"+movieObject.getString("poster_path"));

                        Log.d("err", "onResponse: "+movie.getTitle());
                        moviesL.add(movie);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                recyclerView.setLayoutManager(new LinearLayoutManager(ctx));

                adapter = new MovieAdapter(ctx, moviesL);

                recyclerView.setAdapter(adapter);


            }
        }

                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse: "+ error.getMessage());
                Toast.makeText(ctx, "Error :"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        queue.add(jsonArrayRequest);
    }

}
