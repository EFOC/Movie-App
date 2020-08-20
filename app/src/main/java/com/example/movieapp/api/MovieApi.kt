package com.example.movieapp.api

import com.example.movieapp.model.Movie
import com.example.movieapp.model.MovieList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("https://www.omdbapi.com/")
    fun getMovieInformation(@Query("apikey") apiKey: String, @Query("s") movieName: String): Call<MovieList>

    @GET("https://www.omdbapi.com/")
    fun getMovieDetail(@Query("apikey") apiKey: String, @Query("t") movieId: String): Call<Movie>
}