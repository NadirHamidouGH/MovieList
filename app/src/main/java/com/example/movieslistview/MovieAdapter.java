package com.example.movieslistview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

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
                @Override
                public void onClick(View v) {

                    Toast.makeText(v.getContext(), "Do Something With this Click", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }


}
