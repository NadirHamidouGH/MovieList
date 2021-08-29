package com.example.movieslistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    LayoutInflater inflater;
    List<movie> movieList;

    public MovieAdapter(Context context, List<movie> movies) {
    }

    public void MovieAdapter(Context ctx, List<movie> movies  ){
        this.inflater = LayoutInflater.from(ctx);
        this.movieList = movies;

    }


    public class ViewHolder extends RecyclerView.ViewHolder{
      TextView movieTitle,movieDescription;
      ImageView movieImg;

        public ViewHolder(@NonNull View itemView){

            super(itemView);
            movieTitle = itemView.findViewById(R.id.MovieTitle);
            movieDescription =  itemView.findViewById(R.id.Description);
            movieImg =  itemView.findViewById(R.id.image);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_movies_list_item,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        holder.movieTitle.setText(movieList.get(position).getTitle());
        holder.movieDescription.setText(movieList.get(position).getDescription());
        Picasso.get().load(movieList.get(position).getImgUrl()).into(holder.movieImg);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }


}
