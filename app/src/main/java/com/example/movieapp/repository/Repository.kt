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

    private const val API_KEY = BuildConfig.OMDB_API
    private lateinit var movieList: MutableLiveData<List<Movie>>
    private lateinit var movieDetail: MutableLiveData<Movie>
    private var movieApi: MovieApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://www.omdbapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

         movieApi =  retrofit.create(
            MovieApi::class.java)
    }

    fun getMovieDetail(movieId: String): MutableLiveData<Movie> {
        val call: Call<Movie> = movieApi.getMovieDetail(API_KEY, movieId)
        movieDetail = MutableLiveData()
        call.enqueue(object : Callback<Movie>{
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.d("TEST", "Error: ${t.message}")
            }

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                val movie = response.body()
                Log.d("TEST", "getting details for ${movie?.title}...")
                movieDetail.value = movie
            }
        })
        return movieDetail
    }

    fun getMovieList(movieSearch: String): MutableLiveData<List<Movie>> {
        val call: Call<MovieList> = movieApi.getMovieInformation(API_KEY, movieSearch)
        movieList = MutableLiveData()
        call.enqueue(object: Callback<MovieList>{
            override fun onFailure(call: Call<MovieList>, t: Throwable) {
                Log.d("TEST", "Error: ${t.message}")
            }

            override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                Log.d("TEST", "Success: ${response.body()}")
                val _movieList: MovieList? = response.body()
                _movieList!!.movies.forEach {
                    Log.d("TEST", it.title)
                }
                movieList.value = _movieList.movies
            }

        })
        return movieList
    }
}