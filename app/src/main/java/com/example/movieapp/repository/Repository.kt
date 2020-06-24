package com.example.movieapp.repository

import android.util.Log
import com.example.movieapp.BuildConfig
import com.example.movieapp.model.Movie
import com.example.movieapp.api.MovieApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repository {

    private val API_KEY = BuildConfig.OMDB_API

    fun getMovie() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://www.omdbapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val movieApi: MovieApi =  retrofit.create(
            MovieApi::class.java)

        val call: Call<Movie> = movieApi.getMovieInformation(API_KEY, "Batman")

        call.enqueue(object : Callback<Movie> {
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.d("TEST", "Error: ${t.message}")
            }

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                Log.d("TEST", "Success: ${response.body()}")
                val movie = response.body()
                Log.d("TEST", "${movie!!.title}")
            }

        })
    }
}