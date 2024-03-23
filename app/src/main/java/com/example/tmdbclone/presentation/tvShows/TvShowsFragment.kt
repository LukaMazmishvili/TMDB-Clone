package com.example.tmdbclone.presentation.tvShows

import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.tmdbclone.base.BaseFragment
import com.example.tmdbclone.databinding.FragmentTvShowsBinding
import com.example.tmdbclone.presentation.MainActivityListener
import com.example.tmdbclone.presentation.adapters.GridAdapter
import com.example.tmdbclone.presentation.adapters.MovieAdapter
import com.example.tmdbclone.presentation.movies.MoviesFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TvShowsFragment : BaseFragment<FragmentTvShowsBinding>(FragmentTvShowsBinding::inflate) {

    private val viewModel: TvShowsViewModel by activityViewModels()

    private val adapterAiringToday by lazy {
        MovieAdapter(1)
    }

    private val adapterTrending by lazy {
        MovieAdapter(0)
    }

    private val adapterTopRated by lazy {
        GridAdapter()
    }

    private val adapterPopular by lazy {
        MovieAdapter(0)
    }

    override fun started() {
        setupViews()
    }

    override fun listeners() {

        adapterAiringToday.onItemClickedListener = {
            navigateToDetails(it.id!!, it.title ?: it.originalTitle ?: it.originalName!!)
        }

        adapterTrending.onItemClickedListener = {
            navigateToDetails(it.id!!, it.title ?: it.originalTitle ?: it.originalName!!)
        }

        adapterTopRated.onItemClickedListener = {
            navigateToDetails(it.id!!, it.title ?: it.originalTitle ?: it.originalName!!)
        }

        adapterPopular.onItemClickedListener = {
            navigateToDetails(it.id!!, it.title ?: it.originalTitle ?: it.originalName!!)
        }

    }

    private fun setupViews() {
        with(binding) {

            // Airing Today
            trvAiringTodayTvShows.setTitle("Airing Today")
            trvAiringTodayTvShows.setRecyclerViewAdapter(adapterAiringToday)
            trvAiringTodayTvShows.setSeeAllButtonClickListener {
                findNavController().navigate(
                    TvShowsFragmentDirections.actionTvShowsFragmentToSeeAllFragment(
                        "Tv-Airing Today"
                    )
                )
            }

            // Trending
            trvTrendingTvShows.setTitle("Trending")
            trvTrendingTvShows.setRecyclerViewAdapter(adapterTrending)
            trvTrendingTvShows.setSeeAllButtonClickListener {
                findNavController().navigate(
                    TvShowsFragmentDirections.actionTvShowsFragmentToSeeAllFragment(
                        "Tv-Trending"
                    )
                )
            }

            // Top Rated
            trvTopRatedTvShows.setTitle("Top Rated")
            trvTopRatedTvShows.setGridRecyclerAdapter(adapterTopRated, 4)
            trvTopRatedTvShows.setSeeAllButtonClickListener {
                findNavController().navigate(
                    TvShowsFragmentDirections.actionTvShowsFragmentToSeeAllFragment(
                        "Tv-Top Rated"
                    )
                )
            }

            // Popular
            trvPopularTvShows.setTitle("Popular")
            trvPopularTvShows.setRecyclerViewAdapter(adapterPopular)
            trvPopularTvShows.setSeeAllButtonClickListener {
                findNavController().navigate(
                    TvShowsFragmentDirections.actionTvShowsFragmentToSeeAllFragment(
                        "Tv-Popular"
                    )
                )
            }
        }
    }

    override fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.airingTodayTvShowsState.collect { list ->
                    adapterAiringToday.submitList(list)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.trendingTvShowsState.collect { list ->
                    adapterTrending.submitList(list)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.topRatedTvShowsState.collect { list ->
                    adapterTopRated.submitList(list)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.popularTvShowsState.collect { list ->
                    adapterPopular.submitList(list)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.errorMsg.collect { msg ->
                    if (msg.isNotEmpty()) {
                        Toast.makeText(requireActivity(), msg, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.isLoading.collect { isLoading ->
                    if (!isLoading) {
                        delay(3000)
                        binding.shimmer.visibility = View.GONE
                        binding.dataLayout.visibility = View.VISIBLE
                    } else {
                        binding.shimmer.visibility = View.VISIBLE
                        binding.dataLayout.visibility = View.INVISIBLE
                    }
                }
            }
        }


    }

    override fun onResume() {
        super.onResume()
        val mainActivityListener = activity as MainActivityListener
        mainActivityListener.setToolBarTitle("Tv Shows")
        mainActivityListener.showToolBar()
    }

    private fun navigateToDetails(movieId: Int, movieTitle: String) {
        findNavController().navigate(
            MoviesFragmentDirections.actionGlobalMovieDetailFragment(
                movieTitle,
                "Tv Show",
                movieId
            )
        )
    }
}