package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.MovieSearchItemBinding
import com.example.movieapp.model.Movie
import com.example.movieapp.ui.search.MovieSearchItemViewModel
import com.example.movieapp.ui.search.SearchMovieFragmentViewModel

class MovieListAdapter(val searchMovieFragmentViewModel: SearchMovieFragmentViewModel):
    ListAdapter<Movie, MovieListAdapter.ViewHolder>(MovieDiffCallBack()) {

    lateinit var movieSearchItemViewModel: MovieSearchItemViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val singleItemLayout = LayoutInflater.from(parent.context)
        val movieSearchItemBinding = MovieSearchItemBinding.inflate(singleItemLayout, parent, false)
        return ViewHolder(movieSearchItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    inner class ViewHolder(private var binding: MovieSearchItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.movie = movie
            binding.viewModel = movieSearchItemViewModel
            binding.fragmentViewModel = searchMovieFragmentViewModel
            binding.executePendingBindings()
        }
    }

    fun setCallback(callback: MovieSearchItemViewModel) {
        movieSearchItemViewModel = callback
    }

    class MovieDiffCallBack() : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}