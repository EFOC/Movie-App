package com.example.movieapp.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.model.Movie
import com.example.movieapp.repository.Repository

class SearchMovieFragmentViewModel : ViewModel(), MovieSearchItemViewModel{

    val editTextContent = MutableLiveData<String>()
    var movieList = Repository.getMovieList("batman")
//    var test: MovieSearchItemViewModel = MovieSearchItemViewModel()

    fun buttonClick() {
        Log.d("TEST", "button clicked")
        Log.d("TEST", "displaying text: ${editTextContent.value}")
        this.movieList = getMovieList(editTextContent.value.toString())
    }

    fun getMovieList() {
        Log.d("TEST", "search")
    }

    fun getMovieDetail() {
        Log.d("TEST", "item clicked")

    }

    private fun getMovieDetail(movieId: String) {

    }
    private fun getMovieList(movieSearch: String): MutableLiveData<List<Movie>> = Repository.getMovieList(movieSearch)

    override fun test() {
        Log.d("TEST", "button clicked")
    }

}
