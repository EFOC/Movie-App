package com.example.movieapp.ui.main

import androidx.lifecycle.ViewModel
import com.example.movieapp.repository.Repository

class MainViewModel : ViewModel() {

    private val repo = Repository()

    init {
        repo.getMovie()
    }

}