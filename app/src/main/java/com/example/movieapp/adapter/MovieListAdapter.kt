package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.MovieSearchItemBinding
import com.example.movieapp.model.Movie
import com.squareup.picasso.Picasso

class MovieListAdapter(private var movieList: List<Movie>): RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val singleItemLayout = LayoutInflater.from(parent.context)
        val movieSearchItemBinding = MovieSearchItemBinding.inflate(singleItemLayout, parent, false)
        return ViewHolder(movieSearchItemBinding)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieList[position]
        holder.bind(movie)
    }

    inner class ViewHolder(private var binding: MovieSearchItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.movie = movie
        }
    }
}