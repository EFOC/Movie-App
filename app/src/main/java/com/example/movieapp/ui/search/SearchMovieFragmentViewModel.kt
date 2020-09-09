package com.example.movieapp.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.model.Movie
import com.example.movieapp.repository.Repository

class SearchMovieFragmentViewModel : ViewModel() {

    val editTextContent = MutableLiveData<String>()
    var movieList = Repository.getMovieList("batman")


    fun buttonClick() {
        this.movieList = getMovieList(editTextContent.value.toString())
    }

    fun getMovieDetail(movieId: String) {
        Log.d("TEST", "getting movie with id")
        val movie = Repository.getMovieDetail(movieId)
        Log.d("TEST", "sending back details")
    }
    private fun getMovieList(movieSearch: String): MutableLiveData<List<Movie>> = Repository.getMovieList(movieSearch)

}
