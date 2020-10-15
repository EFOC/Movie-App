package com.example.movieapp.repository

import androidx.lifecycle.MutableLiveData
import com.example.movieapp.BuildConfig
import com.example.movieapp.model.Movie
import com.example.movieapp.api.MovieApi
import com.example.movieapp.util.ApiServiceGenerator

object Repository {

    private const val API_KEY = BuildConfig.THEMOVIEDB;
    private val apiServiceGenerator = ApiServiceGenerator()
    private var movieApi: MovieApi = ApiServiceGenerator.createService()

    fun getMovieDetail(movieId: String): MutableLiveData<Movie> =
        apiServiceGenerator.movieApiCall(movieApi.getMovieDetail(movieId, API_KEY), false) as MutableLiveData<Movie>

    fun getMovieList(movieSearch: String): MutableLiveData<List<Movie>> =
        apiServiceGenerator.movieApiCall(movieApi.getMovieInformation(API_KEY, movieSearch), true) as MutableLiveData<List<Movie>>

    fun getTrendingMovies(): MutableLiveData<List<Movie>> =
        apiServiceGenerator.movieApiCall(movieApi.getTrendingMovies(API_KEY), true) as MutableLiveData<List<Movie>>

    fun getPopularMovies(): MutableLiveData<List<Movie>> =
        apiServiceGenerator.movieApiCall(movieApi.getPopularMovies(API_KEY), true) as MutableLiveData<List<Movie>>
}