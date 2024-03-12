package com.example.tmdbclone.presentation.search.sub_search_fragments

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tmdbclone.base.BaseFragment
import com.example.tmdbclone.databinding.FragmentSearchRecommendationsBinding
import com.example.tmdbclone.presentation.auth.LoginFragment
import com.example.tmdbclone.presentation.auth.RegistrationFragment
import com.example.tmdbclone.presentation.search.SearchPagerAdapter
import com.example.tmdbclone.presentation.search.SearchViewModel
import com.example.tmdbclone.presentation.search.SimilarSearchesAdapter
import com.example.tmdbclone.presentation.search.sub_search_fragments.pager_fragments.AllPageFragment
import com.example.tmdbclone.presentation.tmdb.TMDBFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchRecommendationsFragment :
    BaseFragment<FragmentSearchRecommendationsBinding>(FragmentSearchRecommendationsBinding::inflate) {

    private val viewModel: SearchViewModel by activityViewModels()

    private val args: SearchRecommendationsFragmentArgs by navArgs()

    private val fragmentList = mutableListOf<Fragment?>(null, null, null)

    private val adapterSimilarSearches by lazy {
        SimilarSearchesAdapter()
    }

    private val pagerAdapter by lazy {
        SearchPagerAdapter(this)
    }

    override fun started() {
        binding.etTitle.setText(args.query)

        binding.viewPager.adapter = pagerAdapter

        setupViews()
        search()
        setupTabLayout()

    }

    private fun setupTabLayout() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            for (each in fragmentList) {
                if (each != null) {
                    tab.text = each.id.toString()
                }
            }
        }.attach()
    }

    override fun listeners() {
        with(binding) {

            backButton.setOnClickListener {
                findNavController().popBackStack()
            }

            adapterSimilarSearches.onItemClickListener = { item ->
                viewModel.getSearchedData(item.title ?: item.name!!)
                binding.rvSimilarSearches.visibility = View.GONE
            }

        }

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateFragmentList(pos: Int, fragment: Fragment?) {
        fragmentList[pos] = fragment
        val nonNullList = fragmentList.filterNotNull()
        val constList = listOf(AllPageFragment())
        if (nonNullList.size == 3) {
            val finalList = constList + nonNullList
            pagerAdapter.updateList(finalList)
            pagerAdapter.notifyDataSetChanged()
        } else {
            pagerAdapter.updateList(nonNullList)
            pagerAdapter.notifyDataSetChanged()
        }
        pagerAdapter.notifyDataSetChanged()
    }

    private fun setupViews() {
        binding.rvSimilarSearches.adapter = adapterSimilarSearches
    }

    override fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.getSimilarSearches(binding.etTitle.text.toString())
                viewModel.similarSearchesState.collect { list ->
                    adapterSimilarSearches.submitList(list)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.searchedMoviesState.collect { list ->
                    println(list)
                    if (list.isNotEmpty()) {
                        updateFragmentList(0, RegistrationFragment())
                    } else {
                        updateFragmentList(0, null)
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.searchedTvShowsState.collect { list ->
                    println(list)
                    if (list.isNotEmpty()) {
                        updateFragmentList(1, TMDBFragment())
                    } else {
                        updateFragmentList(1, null)
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.searchedCelebritiesState.collect { list ->
                    println(list)
                    if (list.isNotEmpty()) {
                        updateFragmentList(2, LoginFragment())
                    } else {
                        updateFragmentList(2, null)
                    }
                }
            }
        }
    }


    private fun search() {
        binding.etTitle.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val query = s.toString()
                viewModel.getSimilarSearches(query)
                viewModel.getSearchedData(query)
            }

        })
    }
}