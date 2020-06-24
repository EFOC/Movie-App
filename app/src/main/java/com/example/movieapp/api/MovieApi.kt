package com.example.movieapp.api

import com.example.movieapp.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

//    http://www.omdbapi.com/?apikey=66c04664&t=*broke*
    @GET("https://www.omdbapi.com/")
fun getMovieInformation(@Query("apikey") apiKey: String, @Query("t") movieName: String): Call<Movie>
}