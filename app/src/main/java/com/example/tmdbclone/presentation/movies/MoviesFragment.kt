package com.example.tmdbclone.presentation.movies

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.tmdbclone.base.BaseFragment
import com.example.tmdbclone.common.Endpoints
import com.example.tmdbclone.data.remote.model.PopularMovieDTO
import com.example.tmdbclone.databinding.FragmentMoviesBinding
import com.example.tmdbclone.presentation.MainActivity
import com.example.tmdbclone.presentation.adapters.GridAdapter
import com.example.tmdbclone.presentation.adapters.PopularAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding>(FragmentMoviesBinding::inflate) {

    private val viewModel: MoviesViewModel by activityViewModels()

    private val adapterPopular by lazy {
        PopularAdapter(0)
    }

    private val adapterPIT by lazy {
        PopularAdapter(1)
    }

    private val adapterTrending by lazy {
        PopularAdapter(0)
    }

    private val adapterTopRated by lazy {
        GridAdapter()
    }

    private val adapterUpcoming by lazy {
        PopularAdapter(0)
    }

    override fun started() {

        setupViews()
    }

    override fun listeners() {
        adapterPopular.onItemClickedListener = {
            activity?.supportFragmentManager
            findNavController().navigate(
                MoviesFragmentDirections.actionGlobalMovieDetailFragment(
                    it.id!!
                )
            )
        }
    }

    private fun setupViews() {
        with(binding) {

            // Popular Movies Recycler
            trvPopularMovies.setTitle("Popular")
            trvPopularMovies.setRecyclerViewAdapter(adapterPopular)

            // Playing In Theater Recycler
            trvPlayingInTheater.setTitle("Playing In Theater")
            trvPlayingInTheater.setRecyclerViewAdapter(adapterPIT)

            // Trending Movies Recycler
            trvTrendingMovies.setTitle("Trending")
            trvTrendingMovies.setRecyclerViewAdapter(adapterTrending)

            // Trending Movies Recycler
            trvTopRatedMovies.setTitle("Top Rated")
            trvTopRatedMovies.setGridRecyclerAdapter(adapterTopRated, 4)

            // Trending Movies Recycler
            trvUpcomingMovies.setTitle("Upcoming")
            trvUpcomingMovies.setRecyclerViewAdapter(adapterUpcoming)

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        (activity as MainActivity).setToolBarTitle("Movies")
    }

    override fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.moviesState.collect { list ->
                    adapterPopular.submitList(list)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.playingNowMoviesState.collect { list ->
                    adapterPIT.submitList(list)

                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.trendingMoviesState.collect { list ->
                    adapterTrending.submitList(list)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.topRatedMoviesState.collect { list ->
                    adapterTopRated.submitList(list)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.upcomingMoviesState.collect { list ->
                    adapterUpcoming.submitList(list)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.errorMsg.collect { errorMsg ->
                    if (errorMsg.isNotEmpty()) {
                        Toast.makeText(requireActivity(), errorMsg, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.isLoading.collect { isLoading ->
                    if (!isLoading) {
                        delay(3000)
                        binding.shimmer.visibility = View.INVISIBLE
                        binding.dataLayout.visibility = View.VISIBLE
                    } else {
                        binding.shimmer.visibility = View.VISIBLE
                        binding.dataLayout.visibility = View.INVISIBLE
                    }
                }
            }
        }
    }

}