package com.example.movieapp.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchMovieFragmentViewModel : ViewModel() {

    val editTextContent = MutableLiveData<String>()

    fun buttonClick() {
        Log.d("TEST", "button clicked")
        Log.d("TEST", "displaying text: ${editTextContent.value}")
    }

}