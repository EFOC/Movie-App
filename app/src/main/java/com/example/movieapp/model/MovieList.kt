package com.example.movieapp.model

import com.google.gson.annotations.SerializedName

class MovieList {

    @SerializedName("results")
    lateinit var movies: List<Movie>
}