package com.example.movieapp.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.adapter.MovieListAdapter
import com.example.movieapp.model.Movie
import com.example.movieapp.repository.Repository

class SearchMovieFragmentViewModel : ViewModel() {

    val editTextContent = MutableLiveData<String>()
    var movieList = Repository.getMovieList("batman")
    lateinit var adapter: RecyclerView.Adapter<MovieListAdapter.ViewHolder>
    lateinit var movieListAdapter: RecyclerView

    fun buttonClick() {
        Log.d("TEST", "button clicked")
        Log.d("TEST", "displaying text: ${editTextContent.value}")
        this.movieList = getMovieList(editTextContent.value.toString())
    }

    private fun getMovieList(movieSearch: String): MutableLiveData<List<Movie>> = Repository.getMovieList(movieSearch)
}