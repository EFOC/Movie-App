package com.example.movieapp.ui.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.adapter.MovieListAdapter
import com.example.movieapp.databinding.SearchMovieFragmentBinding
import com.example.movieapp.model.Movie

class SearchMovieFragment : Fragment() {

    companion object {
        fun newInstance() = SearchMovieFragment()
    }

    private lateinit var searchMovieFragmentViewModel: SearchMovieFragmentViewModel
    private lateinit var binding: SearchMovieFragmentBinding
    private lateinit var movieRecyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.search_movie_fragment, container, false)
        searchMovieFragmentViewModel = ViewModelProvider(this).get(SearchMovieFragmentViewModel::class.java)
        binding.viewmodel = searchMovieFragmentViewModel
        setUpRecyclerView(container!!.context)
        return binding.root
    }

    private fun setUpRecyclerView(context: Context) {
        movieRecyclerView = binding.searchMovieFragmentRecyclerView.apply {
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
        val adapter = MovieListAdapter()
        Log.d("TEST", "activity is $activity from setUp")
        binding.searchMovieFragmentRecyclerView.adapter = adapter
        searchMovieFragmentViewModel.movieList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }
}