package com.example.movieapp.ui.search

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
import com.example.movieapp.repository.Repository

class SearchMovieFragment : Fragment() {

    companion object {
        fun newInstance() = SearchMovieFragment()
    }

    private val repo = Repository()
    private lateinit var searchMovieFragmentViewModel: SearchMovieFragmentViewModel
    private lateinit var binding: SearchMovieFragmentBinding
    private lateinit var movieRecyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.search_movie_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        searchMovieFragmentViewModel = ViewModelProvider(this).get(SearchMovieFragmentViewModel::class.java)
        binding.viewmodel = searchMovieFragmentViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        movieRecyclerView = view.findViewById(R.id.search_movie_fragment_recycler_view)
        repo.getMovie().observe(viewLifecycleOwner, Observer {movieList ->
            movieRecyclerView.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            movieRecyclerView.adapter = MovieListAdapter(movieList)
        })
        super.onViewCreated(view, savedInstanceState)
    }
}