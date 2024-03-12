package com.example.tmdbclone.presentation.search.sub_search_fragments.pager_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.tmdbclone.R
import com.example.tmdbclone.base.BaseFragment
import com.example.tmdbclone.databinding.FragmentAllPageBinding
import com.example.tmdbclone.presentation.adapters.CelebritiesAdapter
import com.example.tmdbclone.presentation.adapters.PopularAdapter
import com.example.tmdbclone.presentation.auth.LoginFragment
import com.example.tmdbclone.presentation.auth.RegistrationFragment
import com.example.tmdbclone.presentation.search.SearchViewModel
import com.example.tmdbclone.presentation.tmdb.TMDBFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllPageFragment : BaseFragment<FragmentAllPageBinding>(FragmentAllPageBinding::inflate) {

    private val viewModel: SearchViewModel by activityViewModels()

    private val moviesAdapter by lazy {
        PopularAdapter(0)
    }

    private val tvShowsAdapter by lazy {
        PopularAdapter(1)
    }

    private val celebritiesAdapter by lazy {
        CelebritiesAdapter()
    }

    override fun started() {
        setupViews()
    }

    override fun listeners() {

    }

    private fun setupViews() {

        with(binding) {

            // Movies
            trvMovies.setTitle("Movies")
            trvMovies.setRecyclerViewAdapter(moviesAdapter)

            // Tv Shows
            trvTvShows.setTitle("Tv Shows")
            trvTvShows.setRecyclerViewAdapter(tvShowsAdapter)

            // Celebrities
            trvCelebrities.setTitle("Celebrities")
            trvCelebrities.setRecyclerViewAdapter(celebritiesAdapter)
        }
    }

    override fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.searchedMoviesState.collect { list ->
//                    if (list.isNotEmpty()) {
                    moviesAdapter.submitList(list)
//                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.searchedTvShowsState.collect { list ->
//                    if (list.isNotEmpty()) {
                    tvShowsAdapter.submitList(list)
//                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.searchedCelebritiesState.collect { list ->
//                    if (list.isNotEmpty()) {
                    celebritiesAdapter.submitList(list)
//                    }
                }
            }
        }
    }

}