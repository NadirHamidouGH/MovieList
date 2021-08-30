package com.example.movieslistview

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MovieAdapter(context: Context?, movies: List<movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    var inflater: LayoutInflater
    var movieList: List<movie>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View? = null
        try {
            view = LayoutInflater.from(parent.context).inflate(R.layout.activity_movies_list_item, parent, false)
        } catch (e: Exception) {
            Log.d("err :", "onCreateViewHolder: $e")
        }
        return ViewHolder(view as View)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.movieTitle.text = movieList[position].title
        holder.movieDate.text = movieList[position].date
        Picasso.get().load(movieList[position].imgUrl).into(holder.movieImg)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var movieTitle: TextView
        var movieDescription: TextView? = null
        var movieDate: TextView
        var movieImg: ImageView

        init {
            movieTitle = itemView.findViewById(R.id.MovieTitle)
            movieDate = itemView.findViewById(R.id.MovieDate)
            movieImg = itemView.findViewById(R.id.image)
            itemView.setOnClickListener { v ->
                val itemPostion = layoutPosition
                val intent = Intent(v.context, MovieDetailsActivity::class.java)
                intent.putExtra("title", movieList[itemPostion].title)
                intent.putExtra("date", movieList[itemPostion].date)
                intent.putExtra("note", movieList[itemPostion].note)
                intent.putExtra("desciption", movieList[itemPostion].description)
                intent.putExtra("image", movieList[itemPostion].imgUrl)
                v.context.startActivity(intent)
            }
        }
    }

    init {
        inflater = LayoutInflater.from(context)
        movieList = movies
    }
}