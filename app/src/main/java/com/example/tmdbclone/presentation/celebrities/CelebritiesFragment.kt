package com.example.tmdbclone.presentation.celebrities

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.tmdbclone.base.BaseFragment
import com.example.tmdbclone.data.remote.model.CelebritiesModelDto
import com.example.tmdbclone.databinding.FragmentCelebritiesBinding
import com.example.tmdbclone.domain.model.CelebritiesModel
import com.example.tmdbclone.presentation.MainActivity
import com.example.tmdbclone.presentation.adapters.CelebritiesAdapter
import com.example.tmdbclone.presentation.adapters.CelebritiesGridAdapter
import com.example.tmdbclone.presentation.adapters.GridAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CelebritiesFragment :
    BaseFragment<FragmentCelebritiesBinding>(FragmentCelebritiesBinding::inflate) {

    private val viewModel: CelebritiesViewModel by activityViewModels()

    private val adapterPopular by lazy {
        CelebritiesAdapter()
    }

    private val adapterPopular1 by lazy {
        CelebritiesAdapter()
    }

    private val adapterTrending by lazy {
        CelebritiesGridAdapter()
    }

    override fun started() {
        setupViews()
    }


    override fun listeners() {
        adapterPopular.onItemClickedListener = {
            navigateToDetails(
                it.id!!, it.name!!
            )
        }

        adapterPopular1.onItemClickedListener = {
            navigateToDetails(
                it.id!!, it.name!!
            )
        }

        adapterTrending.onItemClickedListener = {
            navigateToDetails(
                it.id!!, it.name!!
            )
        }
    }

    private fun navigateToDetails(celebrityId: Int, celebrityName: String) {
        findNavController().navigate(
            CelebritiesFragmentDirections.actionGlobalCelebrityDetailsFragment(
                celebrityId = celebrityId, celebrityName = celebrityName
            )
        )
    }

    private fun setupViews() {
        with(binding) {

            // Popular Celebrities
            trvPopularCelebrities.setTitle("Popular")
            trvPopularCelebrities.setRecyclerViewAdapter(adapterPopular)

            // Popular Celebrities Split
            rvPopularSplit.adapter = adapterPopular1

            // Trending Celebrities
            trvTrendingCelebrities.setTitle("Trending")
            trvTrendingCelebrities.setGridRecyclerAdapter(adapterTrending, 4)
        }
    }

    override fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.popularCelebritiesState.collect { list ->
                    list?.let {
                        when (it.results?.isNotEmpty()) {
                            true -> {
                                val splitList = splitList(it.results)
                                adapterPopular.submitList(splitList[0])
                                adapterPopular1.submitList(splitList[1])
                            }

                            else -> {}
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.trendingCelebritiesState.collect { list ->
                    adapterTrending.submitList(list?.results)
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
                    Log.d("isLoading", "observer: $isLoading")
                }
            }
        }
    }

    private fun splitList(list: List<CelebritiesModel.Result>): List<List<CelebritiesModel.Result>> {
        val listSize = list.size
        val middle = listSize / 2
        val list1 = list.subList(0, middle)
        val list2 = list.subList(middle, listSize)

        return listOf(list1, list2)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        (activity as MainActivity).setToolBarTitle("Celebrities")
        (activity as MainActivity).showToolBar()
    }

}