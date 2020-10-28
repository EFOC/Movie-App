package com.example.movieapp.ui.search

import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.example.movieapp.model.Movie
import com.example.movieapp.repository.Repository
import com.example.movieapp.util.FirebaseUserLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SearchMovieFragmentViewModel : ViewModel() {

    val editTextContent = MutableLiveData<String>()
    val finalList = MediatorLiveData<List<Movie>>()
    var signedIn: MutableLiveData<Int> = MutableLiveData()
    lateinit var myRef: DatabaseReference
    lateinit var firebaseDatabase: FirebaseDatabase

    enum class Selection {
        TRENDINGLIST, SEARCHLIST, POPULARLIST
    }

    enum class AuthenticationState {
        AUTHENTICATED, UNAUTHENTICATED, INVALID_AUTHENTICATION
    }

    fun setUpFirebaseDatabase() {
        firebaseDatabase = FirebaseDatabase.getInstance()
        myRef = firebaseDatabase.reference
    }

    fun saveMovieToDatabase(movieId: String) {
        val user = FirebaseAuth.getInstance().currentUser!!.uid
        myRef.child("user").child(user).child(movieId).setValue(true)
    }

    fun checkUserState(state: AuthenticationState) {
        when (state) {
            AuthenticationState.AUTHENTICATED -> userPage()
            AuthenticationState.UNAUTHENTICATED -> anonymizePage()
        }
    }

    private fun anonymizePage() {
        signedIn.postValue(View.GONE)
    }

    private fun userPage() {
        signedIn.postValue(View.VISIBLE)
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
        when(selection){
            Selection.TRENDINGLIST -> addTrendingList()
            Selection.SEARCHLIST -> addSearchList()
            Selection.POPULARLIST -> addPopularList()
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
}