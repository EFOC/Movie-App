package com.example.movieapp.model

import com.google.gson.annotations.SerializedName

class MovieList {

    @SerializedName("Search")
    lateinit var movies: List<Movie>
}