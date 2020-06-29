package com.example.movieapp.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.model.Movie
import com.example.movieapp.repository.Repository

class SearchMovieFragmentViewModel : ViewModel() {

    val editTextContent = MutableLiveData<String>()

    fun buttonClick() {
        Log.d("TEST", "button clicked")
        Log.d("TEST", "displaying text: ${editTextContent.value}")
        getMovieList(editTextContent.value.toString())
    }

    fun getMovieList(movieSearch: String): MutableLiveData<List<Movie>> = Repository.getMovieList(movieSearch)
}