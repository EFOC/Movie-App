package com.example.movieapp.ui.search

import android.view.View
import androidx.lifecycle.*
import com.example.movieapp.model.Movie
import com.example.movieapp.repository.Repository
import com.example.movieapp.util.FirebaseUserLiveData

class SearchMovieFragmentViewModel : ViewModel() {

    val editTextContent = MutableLiveData<String>()
    val finalList = MediatorLiveData<List<Movie>>()
    var signedIn: Boolean = false

    enum class Selection {
        TRENDINGLIST, SEARCHLIST, POPULARLIST
    }

    fun signOutVisible(): Int {
        return when (signedIn) {
            true -> View.VISIBLE
            false -> View.GONE
        }
    }

    fun setSelection(selection: Int) {
        when(selection){
            1 -> addTrendingList()
            2 -> addSearchList()
            3 -> addPopularList()
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

    private fun addPopularList() {
        finalList.removeSource(Repository.getPopularMovies())
        finalList.addSource(Repository.getPopularMovies()) {
            finalList.value = it
        }
    }

    fun getMovieDetail(movieId: String): MutableLiveData<Movie> {
        return Repository.getMovieDetail(movieId)
    }

}
