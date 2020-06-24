package com.example.movieapp.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.R
import com.example.movieapp.repository.Repository

class MainFragment : Fragment() {

    val repo: Repository = Repository()

    companion object {
        fun newInstance() = MainFragment()
    }

//    private lateinit var viewModel: MainViewModel
    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java)}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
        repo.getMovie()
    }

}