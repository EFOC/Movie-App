package com.example.movieapp.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.BuildConfig
import com.example.movieapp.model.Movie
import com.example.movieapp.api.MovieApi
import com.example.movieapp.model.MovieList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Repository {

    private val API_KEY = BuildConfig.OMDB_API
    private var movieList: MutableLiveData<List<Movie>> = MutableLiveData()

    fun getMovie(): MutableLiveData<List<Movie>> {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://www.omdbapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val movieApi: MovieApi =  retrofit.create(
            MovieApi::class.java)

        val call: Call<MovieList> = movieApi.getMovieInformation(API_KEY, "Batman")

        call.enqueue(object: Callback<MovieList>{
            override fun onFailure(call: Call<MovieList>, t: Throwable) {
                Log.d("TEST", "Error: ${t.message}")
            }

            override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                Log.d("TEST", "Success: ${response.body()}")
                val _movieList = response.body()
                _movieList!!.movies.forEach {
                    Log.d("TEST", it.title)
                }
                movieList.value = _movieList.movies
            }

        })
        return movieList
    }
}