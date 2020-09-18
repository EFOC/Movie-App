package com.example.movieapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @SerializedName("original_title") val title: String,
    @SerializedName("overview") val plot: String,
    @SerializedName("release_date") val year: String,
    @SerializedName("id") val id: String,
    @SerializedName("poster_path") val posterImage: String) : Parcelable