package com.example.movieapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainFragmentViewModel : ViewModel() {
    val etUserName = MutableLiveData<String>()
    val etPassword = MutableLiveData<String>()


}