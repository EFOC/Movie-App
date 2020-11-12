package com.example.movieapp.ui.search

import android.view.View
import androidx.lifecycle.*
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.adapter.MovieListAdapter
import com.example.movieapp.model.Movie
import com.example.movieapp.repository.Repository
import com.example.movieapp.util.FireBaseFetcher
import com.example.movieapp.util.FirebaseUserLiveData

class SearchMovieFragmentViewModel : ViewModel() {

    val editTextContent = MutableLiveData<String>()
    val finalList = MediatorLiveData<List<Movie>>()
    val signedIn: MutableLiveData<Int> = MutableLiveData()
    val loadingProgressBar: MutableLiveData<Int> = MutableLiveData()
    lateinit var recyclerView: RecyclerView
    lateinit var itemTouchHelper: ItemTouchHelper
    lateinit var adapter: MovieListAdapter

    enum class Selection {
        TRENDINGLIST, SEARCHLIST, POPULARLIST, USERLIST
    }

    enum class AuthenticationState {
        AUTHENTICATED, UNAUTHENTICATED, INVALID_AUTHENTICATION
    }

    fun saveMovieToDatabase(movieId: String, movieName : String, movieImageUrl: String, moviePlot: String, releaseYear: String) {
        FireBaseFetcher.saveMovieToDatabase(movieId, movieName, movieImageUrl, moviePlot, releaseYear)
    }

    fun removeMovieFromDatabase(movieId: String) {
        FireBaseFetcher.removeMovieFromDatabase(movieId)
    }

    fun checkUserState(state: AuthenticationState) {
        when (state) {
            AuthenticationState.AUTHENTICATED -> userPage()
            AuthenticationState.UNAUTHENTICATED -> anonymizePage()
        }
    }

    var authenticationState = Transformations.map(FirebaseUserLiveData()) { user ->
        if (user != null) {
            AuthenticationState.AUTHENTICATED
        } else {
            AuthenticationState.UNAUTHENTICATED
        }
    }

    fun getMovieDetail(movieId: String): MutableLiveData<Movie> = Repository.getMovieDetail(movieId)

    fun setSelection(selection: Selection) {
        itemTouchHelper.attachToRecyclerView(null)
        loadingProgressBar.postValue(View.VISIBLE)
        when(selection){
            Selection.TRENDINGLIST -> addTrendingList()
            Selection.SEARCHLIST -> addSearchList()
            Selection.POPULARLIST -> addPopularList()
            Selection.USERLIST -> addUsersList()
        }
    }

    private fun addTrendingList() {
        finalList.removeSource(Repository.getTrendingMovies())
        finalList.addSource(Repository.getTrendingMovies()) { movieList ->
            finalList.value = movieList
            loadingProgressBar.postValue(View.GONE)
        }
    }

    private fun addSearchList() {
        if (editTextContent.value != null) {
            finalList.removeSource(Repository.getMovieList(editTextContent.value.toString()))
            finalList.addSource(Repository.getMovieList(editTextContent.value.toString())) { movieList ->
                finalList.value = movieList
                loadingProgressBar.postValue(View.GONE)
            }
        } else {
            loadingProgressBar.postValue(View.GONE)
        }
    }

    private fun addPopularList() {
        finalList.removeSource(Repository.getPopularMovies())
        finalList.addSource(Repository.getPopularMovies()) { movieList ->
            finalList.value = movieList
            loadingProgressBar.postValue(View.GONE)
        }
    }

    private fun addUsersList() {
        finalList.removeSource(FireBaseFetcher.getUserMovies())
        finalList.addSource(FireBaseFetcher.getUserMovies()) { movieList ->
            finalList.value = movieList
            itemTouchHelper.attachToRecyclerView(recyclerView)
            finalList.removeSource(FireBaseFetcher.getUserMovies())
            loadingProgressBar.postValue(View.GONE)
            adapter.notifyDataSetChanged()
        }
    }

    private fun anonymizePage() {
        signedIn.postValue(View.GONE)
    }

    private fun userPage() {
        signedIn.postValue(View.VISIBLE)
    }
}