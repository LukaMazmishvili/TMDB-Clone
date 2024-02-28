package com.example.tmdbclone.presentation.tvShows

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.util.SparseArray
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.tmdbclone.base.BaseFragment
import com.example.tmdbclone.databinding.FragmentTvShowsBinding
import com.example.tmdbclone.presentation.MainActivity
import com.example.tmdbclone.presentation.adapters.GridAdapter
import com.example.tmdbclone.presentation.adapters.PopularAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TvShowsFragment : BaseFragment<FragmentTvShowsBinding>(FragmentTvShowsBinding::inflate) {

    private val viewModel: TvShowsViewModel by activityViewModels()

    private val adapterAiringToday by lazy {
        PopularAdapter(1)
    }

    private val adapterTrending by lazy {
        PopularAdapter(0)
    }

    private val adapterTopRated by lazy {
        GridAdapter()
    }

    private val adapterPopular by lazy {
        PopularAdapter(0)
    }

    override fun started() {
        setupViews()
    }

    override fun listeners() {

    }

    private fun setupViews() {
        with(binding) {

            // Airing Today
            trvAiringTodayTvShows.setTitle("Airing Today")
            trvAiringTodayTvShows.setRecyclerViewAdapter(adapterAiringToday)

            // Trending
            trvTrendingTvShows.setTitle("Trending")
            trvTrendingTvShows.setRecyclerViewAdapter(adapterTrending)

            // Top Rated
            trvTopRatedTvShows.setTitle("Top Rated")
            trvTopRatedTvShows.setGridRecyclerAdapter(adapterTopRated, 4)

            // Popular
            trvPopularTvShows.setTitle("Popular")
            trvPopularTvShows.setRecyclerViewAdapter(adapterPopular)
        }
    }

    override fun observer() {

        viewLifecycleOwner.lifecycleScope.launch() {

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
                        Log.d("isLoadingState", "observer: $isLoading")
                    }
                }
            }

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        (activity as MainActivity).setToolBarTitle("Tv Shows")
    }
}