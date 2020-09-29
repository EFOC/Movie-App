package com.example.movieapp.ui.search

import androidx.lifecycle.*
import com.example.movieapp.model.Movie
import com.example.movieapp.repository.Repository

class SearchMovieFragmentViewModel : ViewModel() {

    val editTextContent = MutableLiveData<String>()
    val finalList = MediatorLiveData<List<Movie>>()


    fun setSelection(selection: Int) {
        when(selection){
            1 -> addTrendingList()
            2 -> addSearchList()
        }
    }

    private fun addTrendingList() {
        finalList.removeSource(Repository.getTrendingMovies())
        finalList.addSource(Repository.getTrendingMovies()) {
            finalList.value = it
        }
    }

    private fun addSearchList() {
        finalList.removeSource(Repository.getMovieList(editTextContent.value.toString()))
        finalList.addSource(Repository.getMovieList(editTextContent.value.toString())) {
            finalList.value = it
        }
    }

    fun getMovieDetail(movieId: String): MutableLiveData<Movie> {
        return Repository.getMovieDetail(movieId)
    }

}
