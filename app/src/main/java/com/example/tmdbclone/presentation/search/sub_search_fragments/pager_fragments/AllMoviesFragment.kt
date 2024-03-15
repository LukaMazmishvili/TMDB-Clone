package com.example.tmdbclone.presentation.search.sub_search_fragments.pager_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingDataAdapter
import com.example.tmdbclone.R
import com.example.tmdbclone.base.BaseFragment
import com.example.tmdbclone.data.remote.model.PopularMovieDTO
import com.example.tmdbclone.databinding.FragmentAllMoviesBinding
import com.example.tmdbclone.presentation.adapters.PopularAdapter
import com.example.tmdbclone.presentation.details.seeAll.adapter.SeeAllAdapter
import com.example.tmdbclone.presentation.search.SearchViewModel
import com.example.tmdbclone.presentation.search.sub_search_fragments.SearchRecommendationsFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllMoviesFragment :
    BaseFragment<FragmentAllMoviesBinding>(FragmentAllMoviesBinding::inflate) {

    private val pagingViewModel: PagingViewModel by activityViewModels()
    private val searchViewModel: SearchViewModel by activityViewModels()

    private val adapter by lazy {
        SeeAllAdapter()
    }

    override fun started() {
        pagingViewModel.getData((parentFragment as SearchRecommendationsFragment).getQuery())
        setupViews()
    }

    override fun listeners() {
        adapter.onItemClickListener = {
            findNavController().navigate(
                AllMoviesFragmentDirections.actionGlobalMovieDetailFragment(
                    it.title ?: it.originalTitle!!, it.id!!
                )
            )
        }
    }

    private fun setupViews() {
        with(binding) {
            RecyclerView.adapter = adapter
        }
    }

    override fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                pagingViewModel.dataState.collect {
                    adapter.submitData(it)
                }
            }
        }
    }

}