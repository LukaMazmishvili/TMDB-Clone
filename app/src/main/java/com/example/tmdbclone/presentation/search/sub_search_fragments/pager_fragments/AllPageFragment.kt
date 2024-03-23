package com.example.tmdbclone.presentation.search.sub_search_fragments.pager_fragments

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.tmdbclone.base.BaseFragment
import com.example.tmdbclone.databinding.FragmentAllPageBinding
import com.example.tmdbclone.presentation.adapters.CelebritiesAdapter
import com.example.tmdbclone.presentation.adapters.MovieAdapter
import com.example.tmdbclone.presentation.search.SearchViewModel
import com.example.tmdbclone.presentation.search.sub_search_fragments.SearchRecommendationsFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllPageFragment : BaseFragment<FragmentAllPageBinding>(FragmentAllPageBinding::inflate) {

    private val viewModel: SearchViewModel by activityViewModels()

    private val moviesAdapter by lazy {
        MovieAdapter(0)
    }

    private val tvShowsAdapter by lazy {
        MovieAdapter(1)
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
            trvMovies.setSeeAllButtonClickListener {
                (parentFragment as SearchRecommendationsFragment).changeViewPagerItem(1)
            }

            // Tv Shows
            trvTvShows.setTitle("Tv Shows")
            trvTvShows.setRecyclerViewAdapter(tvShowsAdapter)
            trvTvShows.setSeeAllButtonClickListener {
                (parentFragment as SearchRecommendationsFragment).changeViewPagerItem(2)
            }

            // Celebrities
            trvCelebrities.setTitle("Celebrities")
            trvCelebrities.setRecyclerViewAdapter(celebritiesAdapter)
            trvCelebrities.setSeeAllButtonClickListener {
                (parentFragment as SearchRecommendationsFragment).changeViewPagerItem(3)
            }
        }
    }

    override fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.searchedMoviesState.collect { list ->
                    moviesAdapter.submitList(list)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.searchedTvShowsState.collect { list ->
                    tvShowsAdapter.submitList(list)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.searchedCelebritiesState.collect { list ->
                    celebritiesAdapter.submitList(list)
                }
            }
        }
    }

}