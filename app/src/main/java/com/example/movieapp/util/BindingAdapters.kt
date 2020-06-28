package com.example.movieapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("android:src")
fun imageViewSrc(view: ImageView, url: String) {
    Picasso.get().load(url).into(view)
}