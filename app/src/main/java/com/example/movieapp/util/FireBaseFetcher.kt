package com.example.movieapp.util

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.model.Movie
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

object FireBaseFetcher {
    private val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val myRef: DatabaseReference = firebaseDatabase.reference
    val liveDataMovies: MutableLiveData<ArrayList<Movie>> = MutableLiveData()

    fun saveMovieToDatabase(movieId: String, movieName : String,movieImageUrl: String) {
        Log.d("TEST", "Saving movie to database")
        val user = FirebaseAuth.getInstance().currentUser!!.uid
        myRef.child("users").child(user).child("saved_movies").child(movieId).child("movie_name").setValue(movieName)
        myRef.child("users").child(user).child("saved_movies").child(movieId).child("movie_poster_url").setValue(movieImageUrl)
    }

    fun removeMovieFromDatabase(movieId: String) {
        Log.d("TEST", "Removing movie from database")
        val user = FirebaseAuth.getInstance().currentUser!!.uid
        myRef.child("users").child(user).child("saved_movies").child(movieId).child("movie_name").removeValue()
        myRef.child("users").child(user).child("saved_movies").child(movieId).child("movie_poster_url").removeValue()
    }

    fun getUserMovies(): LiveData<List<Movie>> {
        Log.d("TEST", "Getting user movies")

        myRef.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(ds: DataSnapshot) {
                Log.d("TEST", "Getting snapshot of database")
                val user = FirebaseAuth.getInstance().currentUser!!.uid
                val arrayList: ArrayList<Movie> = ArrayList()
                for (dataSnapshot in ds.children) {
                    dataSnapshot.child(user).child("saved_movies").children.forEach {
                        arrayList.add(Movie(it.child("movie_name").value.toString(), "test", " test", it.key.toString(), it.child("movie_poster_url").value.toString()))
                    }
                }
                liveDataMovies.value = arrayList
            }
        })
        return liveDataMovies as LiveData<List<Movie>>
    }
}