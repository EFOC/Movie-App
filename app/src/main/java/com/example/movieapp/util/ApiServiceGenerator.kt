package com.example.movieapp.util

import com.example.movieapp.api.MovieApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServiceGenerator {

    private lateinit var movieApi: MovieApi

    init {
        createService()
    }

    fun getService(): MovieApi {
        return movieApi
    }

    private fun createService() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://www.omdbapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val movieApi: MovieApi =  retrofit.create(
            MovieApi::class.java)
    }
}