package com.example.tmdbclone.presentation.search.sub_search_fragments

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.widget.SearchView
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
import com.example.tmdbclone.presentation.details.seeAll.SeeAllFragment
import com.example.tmdbclone.presentation.search.SearchPagerAdapter
import com.example.tmdbclone.presentation.search.SearchViewModel
import com.example.tmdbclone.presentation.search.SimilarSearchesAdapter
import com.example.tmdbclone.presentation.search.sub_search_fragments.pager_fragments.AllCelebritiesFragment
import com.example.tmdbclone.presentation.search.sub_search_fragments.pager_fragments.AllMoviesFragment
import com.example.tmdbclone.presentation.search.sub_search_fragments.pager_fragments.AllPageFragment
import com.example.tmdbclone.presentation.search.sub_search_fragments.pager_fragments.AllTvShowsFragment
import com.example.tmdbclone.presentation.tmdb.TMDBFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
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

    override fun started() {
        binding.etTitle.setText(args.query)
        viewModel.setQuery(args.query)

        setupViews()
        search()

    }

    private fun setupTabLayout(finalList: List<Fragment>) {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            if (finalList.size == 1) {
                binding.tabLayout.visibility = View.GONE
            } else {
                binding.tabLayout.visibility = View.VISIBLE
                finalList[position].let { fragment ->
                    when (fragment) {
                        is AllPageFragment -> tab.text = "All"
                        is AllMoviesFragment -> tab.text = "Movies"
                        is AllTvShowsFragment -> tab.text = "Tv Shows"
                        is AllCelebritiesFragment -> tab.text = "Celebrities"
                        else -> tab.text = "null"
                    }
                }
            }
        }.attach()
    }

    override fun listeners() {
        with(binding) {

            btnClearText.setOnClickListener {
                etTitle.setText("")
            }

            backButton.setOnClickListener {
                findNavController().popBackStack()
            }

            adapterSimilarSearches.onItemClickListener = { item ->
                val query = item.title ?: item.name!!
                binding.etTitle.apply {
                    setText(query)
                    clearFocus()
                }
                viewModel.apply {
                    getSearchedMovies(query)
                    getSearchedTvShows(query)
                    getSearchedCelebrities(query)
                }
            }

        }
    }

    private fun updateFragmentList(pos: Int, fragment: Fragment?) {
        val pagerAdapter: SearchPagerAdapter?
        fragmentList[pos] = fragment
        val nonNullList = fragmentList.filterNotNull()
        val constList = listOf(AllPageFragment())
        val finalList = if (nonNullList.size == 3) {
            constList + nonNullList
        } else {
            nonNullList
        }
        pagerAdapter = SearchPagerAdapter(this, finalList)
        binding.viewPager.adapter = pagerAdapter
        setupTabLayout(finalList)
    }

    private fun setupViews() {
        with(binding) {
            rvSimilarSearches.adapter = adapterSimilarSearches
            etTitle.apply {
                imeOptions = EditorInfo.IME_ACTION_SEARCH
                maxLines = 1
            }
        }
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
                        updateFragmentList(0, AllMoviesFragment())
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
                        updateFragmentList(1, AllTvShowsFragment())
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
                        updateFragmentList(2, AllCelebritiesFragment())
                    } else {
                        updateFragmentList(2, null)
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.isLoading.collect { isLoading ->
                    if (isLoading) {
                        binding.progressBarWrapper.visibility = View.VISIBLE
                    } else {
                        delay(200)
                        binding.progressBarWrapper.visibility = View.GONE
                    }
                }
            }
        }

    }


    private fun search() {
        with(binding) {
            etTitle.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    viewPager.visibility = View.GONE
                    rvSimilarSearches.visibility = View.VISIBLE
                    tabLayout.visibility = View.GONE
                } else {
                    viewPager.visibility = View.VISIBLE
                    rvSimilarSearches.visibility = View.GONE
                    tabLayout.visibility = View.VISIBLE
                }
            }

            etTitle.addTextChangedListener(object : TextWatcher {

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    val query = s.toString()
                    viewModel.getSimilarSearches(query)
                }

                override fun afterTextChanged(s: Editable?) {}

            })

            etTitle.setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_SEARCH -> {
                        binding.etTitle.clearFocus()
                        val query = binding.etTitle.text.toString()
                        viewModel.getSearchedMovies(query)
                        viewModel.getSearchedTvShows(query)
                        viewModel.getSearchedCelebrities(query)


                        viewPager.visibility = View.VISIBLE
                        rvSimilarSearches.visibility = View.GONE
                        tabLayout.visibility = View.VISIBLE
                        true
                    }

                    else -> false
                }
            }
        }
    }
}