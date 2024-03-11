package com.example.tmdbclone.presentation.search.sub_search_fragments

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tmdbclone.base.BaseFragment
import com.example.tmdbclone.data.remote.model.SearchSimilarModelDto
import com.example.tmdbclone.databinding.FragmentSearchRecommendationsBinding
import com.example.tmdbclone.presentation.auth.LoginFragment
import com.example.tmdbclone.presentation.auth.RegistrationFragment
import com.example.tmdbclone.presentation.details.seeAll.SeeAllFragment
import com.example.tmdbclone.presentation.movies.MoviesFragment
import com.example.tmdbclone.presentation.search.SearchPagerAdapter
import com.example.tmdbclone.presentation.search.SearchViewModel
import com.example.tmdbclone.presentation.search.SimilarSearchesAdapter
import com.example.tmdbclone.presentation.search.sub_search_fragments.pager_fragments.AllPageFragment
import com.example.tmdbclone.presentation.tmdb.TMDBFragment
import com.example.tmdbclone.presentation.tvShows.TvShowsFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchRecommendationsFragment :
    BaseFragment<FragmentSearchRecommendationsBinding>(FragmentSearchRecommendationsBinding::inflate) {

    private val viewModel: SearchViewModel by viewModels()

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

    private fun updateFragmentList(pos: Int, fragment: Fragment) {
        fragmentList[pos] = fragment
        val nonNullList = mutableListOf<Fragment>()
        val tempList = listOf<Fragment>(AllPageFragment())
        fragmentList.forEach {
            if (it != null) {
                nonNullList.add(it)
            }
        }
        if (nonNullList.size == 3) {
            nonNullList.add(0, AllPageFragment())
            pagerAdapter.count = nonNullList
        } else {
            pagerAdapter.count = nonNullList
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
                viewModel.similarSearchesState.collect { list ->
                    if (list.isNotEmpty()) {
                        updateFragmentList(0, RegistrationFragment())
                    } else {
//                        fragmentList.add(1, null)
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.similarSearchesState.collect { list ->
                    if (list.isNotEmpty()) {
//                        fragmentList.add(2, SeeAllFragment())
                        updateFragmentList(1, TMDBFragment())
                    } else {
//                        fragmentList.add(2, null)
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.similarSearchesState.collect { list ->
                    if (list.isNotEmpty()) {
//                        countList.add(3, SeeAllFragment())
                        updateFragmentList(2, LoginFragment())
                    } else {
//                        countList.add(3, null)
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
//                pagerAdapter.clearList()
                viewModel.getSimilarSearches(query)
            }

        })
    }
}