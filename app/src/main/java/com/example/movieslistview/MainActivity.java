package com.example.movieslistview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

///
import android.content.Context;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {



    RecyclerView recyclerView;
    List<movie> movies;

    private static String JSON_URL = "https://api.themoviedb.org/3/discover/movie?api_key=08fce566e3d5a9aa500c2ccf5c32eb94";
    MovieAdapter adapter;
    private Object RequestQueue;


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

        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.listMoviesRecyclerView);
        movies = new ArrayList<>();

        try {
            extractMovies();

        }catch (Exception e){
            Log.d("Extraction movies  ", "Error : " + e);
        }




    }






    private void extractMovies() {

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {


            @SuppressLint("LongLogTag")
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

                        Log.d("title : ===============> ", "onResponse: "+movie.getTitle());
                        movies.add(movie);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                adapter = new MovieAdapter(getApplicationContext(), movies);

                recyclerView.setAdapter(adapter);


            }
        }

        , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse: "+ error.getMessage());
                Toast.makeText(MainActivity.this, "Error :"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        queue.add(jsonArrayRequest);
    }




}