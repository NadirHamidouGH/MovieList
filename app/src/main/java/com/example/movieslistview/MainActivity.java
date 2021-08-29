package com.example.movieslistview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


 /*   ListView listView;

    String movieTitle[] = {"movie1", "movie2" , "movie3", "movie4", "movie5"};
    String movieDescription[] = {"movie1 lorem azlemazek az aze ", "movie2  ade scro iton ea", "movie3 ovie ade sc" , "des zakdalze " , "descrrp"};
*/
//    int imageList[] = {R.drawable.facebook, R.drawable.whatsapp, R.drawable.twitter, R.drawable.instagram , R.drawable.youtube};


    RecyclerView recyclerView;
    List<movie> movies;

    private static String JSON_URL = "https://api.themoviedb.org/3/discover/movie ";
    MovieAdapter adapter;
    private Object RequestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    recyclerView = findViewById(R.id.listMovies);
    movies= new ArrayList<>();

        try {
            extractMovies();

        }catch (Exception e){
            Log.d("Extraction movies err ", "onCreate: " + e);
        }























        /*  listView Method 2

        listView = findViewById(R.id.listOfMovies);

        MyAdapter adpter = new MyAdapter(this, movieTitle, movieDescription, imageList);

        listView.setAdapter(adpter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position ==  0) {
                    Toast.makeText(MainActivity.this, "Facebook Description", Toast.LENGTH_SHORT).show();
                }
                if (position ==  1) {
                    Toast.makeText(MainActivity.this, "Whatsapp Description", Toast.LENGTH_SHORT).show();
                }
                if (position ==  2) {
                    Toast.makeText(MainActivity.this, "Twitter Description", Toast.LENGTH_SHORT).show();
                }
                if (position ==  3) {
                    Toast.makeText(MainActivity.this, "Instagram Description", Toast.LENGTH_SHORT).show();
                }
                if (position ==  4) {
                    Toast.makeText(MainActivity.this, "Youtube Description", Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }

    private void extractMovies() {


        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {

                    try {
                        JSONObject movieObject = response.getJSONObject(i);

                        movie movie = new movie();
                        movie.setTitle(movieObject.getString("title").toString());
                        movie.setDescription(movieObject.getString("overview").toString());
                        movie.setImgUrl(movieObject.getString("poster_path").toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                adapter = new MovieAdapter(getApplicationContext(),movies);

                recyclerView.setAdapter(adapter);


            }
        }

        , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse: "+ error.getMessage());
            }
        });

        queue.add(jsonArrayRequest);
    }


    /*class MyAdapter extends ArrayAdapter<String>{
        Context context;
        String MovieTitle[];
        String MovieDescription[];
        int MovieImgs[];

        MyAdapter (Context c, String title[], String description[], int imgs[]) {
            super(c, R.layout.activity_movies_list_item, R.id.listOfMovies, title);
            this.context = c;
            this.MovieTitle = title;
            this.MovieDescription = description;
            this.MovieImgs = imgs;

        }


        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.activity_movies_list_item, parent, false);
            ImageView images = row.findViewById(R.id.image);
            TextView myTitle = row.findViewById(R.id.MovieTitle);
            TextView myDescription = row.findViewById(R.id.Description);

            // now set our resources on views
            images.setImageResource(imageList[position]);
            myTitle.setText(movieTitle[position]);
            myDescription.setText(movieDescription[position]);




            return row;
        }


    }*/




}