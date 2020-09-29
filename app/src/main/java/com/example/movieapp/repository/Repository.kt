package com.example.movieapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
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

    private const val API_KEY = BuildConfig.THEMOVIEDB;
    private lateinit var liveDataMovies: MutableLiveData<Any>
    private var movieApi: MovieApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

         movieApi =  retrofit.create(
            MovieApi::class.java)
    }

    private fun <T> serviceGen(call: Call<T>, isList: Boolean): MutableLiveData<Any> {
        liveDataMovies = MutableLiveData()
        call.enqueue(object : Callback<T>{
            override fun onFailure(call: Call<T>, t: Throwable) {
                Log.d("TEST", "Error: ${t.message}")
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                val movie = response.body()
                if (isList)
                    liveDataMovies.value = (movie as MovieList).movies
                else
                    liveDataMovies.value = movie
            }
        })
        return liveDataMovies
    }

    fun getMovieDetail(movieId: String): MutableLiveData<Movie> =
        serviceGen(movieApi.getMovieDetail(movieId, API_KEY), false) as MutableLiveData<Movie>

    fun getMovieList(movieSearch: String): MutableLiveData<List<Movie>> =
        serviceGen(movieApi.getMovieInformation(API_KEY, movieSearch), true) as MutableLiveData<List<Movie>>

    fun getTrendingMovies(): MutableLiveData<List<Movie>> =
        serviceGen(movieApi.getTrendingMovies(API_KEY), true) as MutableLiveData<List<Movie>>

    fun getPopularMovies(): MutableLiveData<List<Movie>> =
        serviceGen(movieApi.getPopularMovies(API_KEY), true) as MutableLiveData<List<Movie>>
}