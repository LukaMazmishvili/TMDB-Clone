package com.example.tmdbclone.presentation.search.sub_search_fragments.pager_fragments

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdbclone.base.BaseFragment
import com.example.tmdbclone.data.remote.model.CelebritiesModelDto
import com.example.tmdbclone.databinding.FragmentAllCelebritiesBinding
import com.example.tmdbclone.domain.model.CelebritiesModel
import com.example.tmdbclone.presentation.search.SearchViewModel
import com.example.tmdbclone.presentation.search.sub_search_fragments.SearchRecommendationsFragment
import com.example.tmdbclone.presentation.search.sub_search_fragments.pager_fragments.adapter.CelebsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllCelebritiesFragment :
    BaseFragment<FragmentAllCelebritiesBinding>(FragmentAllCelebritiesBinding::inflate) {

    private val searchViewModel: SearchViewModel by viewModels()

    private var currentPage = 1
    private var isLastPage = false
    private var isLoading = false

    private var currentFinalList = mutableListOf<CelebritiesModel.Result>()

    private val adapter by lazy {
        CelebsAdapter()
    }

    override fun started() {
        setupViews()
    }

    override fun listeners() {
        val query = (parentFragment as SearchRecommendationsFragment).getQuery()

        if (currentFinalList.isEmpty()) {
            searchViewModel.getSearchedCelebrities((parentFragment as SearchRecommendationsFragment).getQuery())
        }

        binding.root.adapter = adapter
        binding.root.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!isLoading && !isLastPage && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0 && totalItemCount >= 20
                ) {
                    searchViewModel.getSearchedCelebrities(query, ++currentPage)
                }
            }
        })
    }

    private fun setupViews() {
        binding.root.adapter = adapter
    }

    override fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            searchViewModel.searchedCelebritiesState.collect {
                val tempList = currentFinalList + it
                currentFinalList = tempList.toMutableList()
                adapter.submitList(tempList)
            }
        }
    }

}