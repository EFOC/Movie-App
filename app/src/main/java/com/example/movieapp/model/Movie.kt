package com.example.movieapp.model

import com.google.gson.annotations.SerializedName

class Movie {

    @SerializedName("Title")
    lateinit var title: String

    @SerializedName("Year")
    lateinit var year: String

    @SerializedName("imdbID")
    lateinit var id: String

    @SerializedName("Poster")
    lateinit var posterImage: String

}