package com.example.movieapp.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.model.Movie
import com.example.movieapp.repository.Repository

class SearchMovieFragmentViewModel : ViewModel(), MovieSearchItemViewModel {

    val editTextContent = MutableLiveData<String>()
    var movieList = Repository.getMovieList("batman")
    val test: SearchMovieRecyclerViewItemCallBack = SearchMovieFragment()

    fun buttonClick() {
        Log.d("TEST", "button clicked")
        Log.d("TEST", "displaying text: ${editTextContent.value}")
        this.movieList = getMovieList(editTextContent.value.toString())
    }

    fun getMovieList() {
        Log.d("TEST", "search")
    }

    private fun getMovieDetail() {
        Log.d("TEST", "item clicked")

    }

    private fun getMovieDetail(movieId: String) {
        Log.d("TEST", "getting movie with id")
        val movie = Repository.getMovieDetail(movieId)
        Log.d("TEST", "sending back details")
        test.onItemClickCallBack(movie.value)
    }
    private fun getMovieList(movieSearch: String): MutableLiveData<List<Movie>> = Repository.getMovieList(movieSearch)

    override fun displayMovieDetailsButton(movieId: String) {
        Log.d("TEST", "button clicked $movieId")
        getMovieDetail(movieId)
    }
}

interface SearchMovieRecyclerViewItemCallBack {
    fun onItemClickCallBack(movie: Movie?)
}