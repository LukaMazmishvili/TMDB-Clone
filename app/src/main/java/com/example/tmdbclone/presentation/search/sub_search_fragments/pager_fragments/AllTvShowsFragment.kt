package com.example.tmdbclone.presentation.search.sub_search_fragments.pager_fragments

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdbclone.base.BaseFragment
import com.example.tmdbclone.data.remote.model.PopularMovieDTO
import com.example.tmdbclone.databinding.FragmentAllTvShowsBinding
import com.example.tmdbclone.presentation.adapters.PopularAdapter
import com.example.tmdbclone.presentation.details.seeAll.adapter.SeeAllAdapter
import com.example.tmdbclone.presentation.search.SearchViewModel
import com.example.tmdbclone.presentation.search.sub_search_fragments.SearchRecommendationsFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllTvShowsFragment :
    BaseFragment<FragmentAllTvShowsBinding>(FragmentAllTvShowsBinding::inflate) {

    private val searchViewModel: SearchViewModel by viewModels()

    private var currentPage = 1
    private var isLastPage = false
    private var isLoading = false

    private var currentFinalList = mutableListOf<PopularMovieDTO.MovieModelDto>()

    private val adapter by lazy {
        PopularAdapter(0)
    }

    override fun started() {
        val query = (parentFragment as SearchRecommendationsFragment).getQuery()

        if (currentFinalList.isEmpty()) {
            searchViewModel.getSearchedTvShows((parentFragment as SearchRecommendationsFragment).getQuery())
        }

        binding.rvTvShows.adapter = adapter
        binding.rvTvShows.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!isLoading && !isLastPage && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0 && totalItemCount >= 20
                ) {
                    searchViewModel.getSearchedTvShows(query, ++currentPage)
                }
            }
        })

    }

    override fun listeners() {

    }

    override fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            searchViewModel.searchedTvShowsState.collect {
                val tempList = currentFinalList + it
                currentFinalList = tempList.toMutableList()
                adapter.submitList(tempList)
            }
        }
    }
}