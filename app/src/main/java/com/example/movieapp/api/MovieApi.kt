package com.example.movieapp.api

import com.example.movieapp.model.Movie
import com.example.movieapp.model.MovieList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("https://api.themoviedb.org/3/search/movie?")
    fun getMovieInformation(@Query("api_key") apiKey: String, @Query("query") movieName: String): Call<MovieList>

    @GET("https://api.themoviedb.org/3/trending/movie/day")
    fun getTrendingMovies(@Query("api_key") apiKey: String): Call<MovieList>

    @GET("https://api.themoviedb.org/3/movie/popular?}")
    fun getPopularMovies(@Query("api_key") movieId: String): Call<MovieList>

    @GET("https://api.themoviedb.org/3/movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") movieId: String, @Query("api_key") apiKey: String): Call<Movie>
}