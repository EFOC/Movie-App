package com.example.movieapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class MovieList(@SerializedName("results") val movies: List<Movie>): Parcelable