package com.example.movieapp.ui.search

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.adapter.MovieListAdapter
import com.example.movieapp.databinding.SearchMovieFragmentBinding
import com.example.movieapp.ui.search.SearchMovieFragmentViewModel.Selection.*
import com.firebase.ui.auth.AuthUI
import java.util.*

class SearchMovieFragment : Fragment(), MovieSearchItemViewModel {

    companion object {
        fun newInstance() = SearchMovieFragment()
    }

    private lateinit var searchMovieFragmentViewModel: SearchMovieFragmentViewModel
    private lateinit var binding: SearchMovieFragmentBinding
    private lateinit var movieRecyclerView: RecyclerView
    private lateinit var adapter: MovieListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.search_movie_fragment, container, false)
        searchMovieFragmentViewModel = ViewModelProvider(this).get(SearchMovieFragmentViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewmodel = searchMovieFragmentViewModel

        binding.signOutButton.setOnClickListener { AuthUI.getInstance().signOut(requireContext()) }

        searchMovieFragmentViewModel.authenticationState.observe(viewLifecycleOwner, Observer { state ->
            searchMovieFragmentViewModel.checkUserState(state)
        })
        setUpRecyclerView(container!!.context)
        return binding.root
    }

    private fun setUpRecyclerView(context: Context) {
        movieRecyclerView = binding.searchMovieFragmentRecyclerView.apply {
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
        searchMovieFragmentViewModel.recyclerView = movieRecyclerView
        searchMovieFragmentViewModel.itemTouchHelper = setUpItemTouch()
        adapter = MovieListAdapter(searchMovieFragmentViewModel)
        adapter.setCallback(this)
        binding.searchMovieFragmentRecyclerView.adapter = adapter
        searchMovieFragmentViewModel.setSelection(TRENDINGLIST)
        searchMovieFragmentViewModel.finalList.observe(viewLifecycleOwner, Observer {movieList ->
            adapter.submitList(movieList)
            binding.searchMovieFragmentRecyclerView.scrollToPosition(0)
        })
    }

    private fun setUpItemTouch(): ItemTouchHelper {
        return ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                searchMovieFragmentViewModel.removeMovieFromDatabase(adapter.getItemAt(viewHolder.adapterPosition).id)
                searchMovieFragmentViewModel.setSelection(USERLIST)
                adapter.notifyItemRemoved(viewHolder.adapterPosition)
            }
        })
    }

    override fun displayMovieDetailsButton(movieId: String) {
        searchMovieFragmentViewModel.getMovieDetail(movieId).observe(viewLifecycleOwner, Observer {movie ->
            val bundle = bundleOf("movie" to movie)
            view?.findNavController()?.navigate(R.id.movieDetailFragment, bundle)
        })
    }
}