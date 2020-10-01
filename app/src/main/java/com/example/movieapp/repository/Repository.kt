package com.example.movieapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.BuildConfig
import com.example.movieapp.model.Movie
import com.example.movieapp.api.MovieApi
import com.example.movieapp.model.MovieList
import com.example.movieapp.util.ApiServiceGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Repository {

    private const val API_KEY = BuildConfig.THEMOVIEDB;
    private val apiServiceGenerator = ApiServiceGenerator()
    private var movieApi: MovieApi = ApiServiceGenerator.createService()


    fun getMovieDetail(movieId: String): MutableLiveData<Movie> =
        apiServiceGenerator.movieaApiCall(movieApi.getMovieDetail(movieId, API_KEY), false) as MutableLiveData<Movie>

    fun getMovieList(movieSearch: String): MutableLiveData<List<Movie>> =
        apiServiceGenerator.movieaApiCall(movieApi.getMovieInformation(API_KEY, movieSearch), true) as MutableLiveData<List<Movie>>

    fun getTrendingMovies(): MutableLiveData<List<Movie>> =
        apiServiceGenerator.movieaApiCall(movieApi.getTrendingMovies(API_KEY), true) as MutableLiveData<List<Movie>>

    fun getPopularMovies(): MutableLiveData<List<Movie>> =
        apiServiceGenerator.movieaApiCall(movieApi.getPopularMovies(API_KEY), true) as MutableLiveData<List<Movie>>
}