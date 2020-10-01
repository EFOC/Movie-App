package com.example.movieapp.util

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.api.MovieApi
import com.example.movieapp.model.MovieList
import com.example.movieapp.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServiceGenerator {

    companion object {
        fun createService(): MovieApi {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(
                MovieApi::class.java)
        }
    }
    init {
        createService()
    }

    fun <T> movieaApiCall(call: Call<T>, isList: Boolean) : MutableLiveData<Any> {
        val liveDataMovies: MutableLiveData<Any> = MutableLiveData()
        call.enqueue(object : Callback<T> {
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
}