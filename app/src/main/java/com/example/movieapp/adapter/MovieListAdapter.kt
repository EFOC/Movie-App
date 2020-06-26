package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.model.Movie
import com.squareup.picasso.Picasso

class MovieListAdapter(private var movieList: List<Movie>): RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val singleItemLayout = LayoutInflater.from(parent.context).inflate(R.layout.movie_search_item, parent, false)
        return ViewHolder(singleItemLayout)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = movieList[position].title
        holder.year.text = movieList[position].year
        Picasso.get().load(movieList[position].posterImage).into(holder.poster)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.search_movie_item_title)
        val year: TextView = itemView.findViewById(R.id.search_movie_item_year)
        val poster: ImageView = itemView.findViewById(R.id.search_movie_item_poster)
    }
}