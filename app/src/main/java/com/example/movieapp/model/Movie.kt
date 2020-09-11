package com.example.movieapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @SerializedName("Title") val title: String,
    @SerializedName("Plot") val plot: String,
    @SerializedName("Year") val year: String,
    @SerializedName("imdbID") val id: String,
    @SerializedName("Poster") val posterImage: String) : Parcelable