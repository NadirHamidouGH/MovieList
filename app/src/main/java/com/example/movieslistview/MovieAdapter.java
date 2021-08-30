package com.example.movieslistview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    LayoutInflater inflater;
    List<movie> movieList;


    public MovieAdapter(Context context, List<movie> movies) {
        this.inflater = LayoutInflater.from(context);
        //this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        this.movieList = movies;

    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;

        try{

            view //= inflater.inflate(R.layout.activity_movies_list_item,parent,false);

            = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_movies_list_item, parent, false);

        }


        catch (Exception e){
            Log.d("err :", "onCreateViewHolder: "+ e);
        }

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        holder.movieTitle.setText(movieList.get(position).getTitle());
        holder.movieDate.setText(movieList.get(position).getDate());
      // holder.movieDescription.setText(movieList.get(position).getDescription());
        Picasso.get().load(movieList.get(position).getImgUrl()).into(holder.movieImg);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView movieTitle,movieDescription,movieDate;
        ImageView movieImg;

        public ViewHolder(@NonNull View itemView){

            super(itemView);
            movieTitle = itemView.findViewById(R.id.MovieTitle);
            movieDate = itemView.findViewById(R.id.MovieDate);
           // movieDescription =  itemView.findViewById(R.id.Description);
            movieImg =  itemView.findViewById(R.id.image);


            itemView.setOnClickListener(new View.OnClickListener() {

              //  context = itemView.getContext();
                @Override
                public void onClick(View v) {
                    int itemPostion = getLayoutPosition();
                   // Toast.makeText(v.getContext(), "Do Something With this Click" + itemPostion + "" +movieList.get(itemPostion).getTitle(), Toast.LENGTH_SHORT).show();

                  //  Intent myIntent = new Intent(MainActivity.this, MovieDetailsActivity.class);
                  //  MainActivity.this.startActivity(myIntent);

                    Intent intent = new Intent(v.getContext(), MovieDetailsActivity.class);
                    intent.putExtra("title", movieList.get(itemPostion).getTitle());
                    intent.putExtra("date", movieList.get(itemPostion).getDate());
                    intent.putExtra("desciption", movieList.get(itemPostion).getDescription());
                    intent.putExtra("image", movieList.get(itemPostion).getImgUrl());

                    v.getContext().startActivity(intent);
                }
            });

        }
    }


}
