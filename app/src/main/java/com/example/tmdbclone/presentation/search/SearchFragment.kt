package com.example.tmdbclone.presentation.search

import android.os.Build
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.get
import androidx.core.view.iterator
import androidx.core.view.size
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.tmdbclone.base.BaseFragment
import com.example.tmdbclone.databinding.FragmentSearchBinding
import com.example.tmdbclone.extension.insertTextViews
import com.example.tmdbclone.presentation.MainActivity
import com.example.tmdbclone.presentation.movies.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate),
    View.OnClickListener {

    private val moviesViewModel: MoviesViewModel by activityViewModels()

    override fun started() {

    }

    override fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                moviesViewModel.trendingMoviesState.collect { list ->
                    val listOfStrings = mutableListOf<String>()
                    for (i in list) {
                        listOfStrings.add(i.title ?: i.originalName!!)
                    }
                    with(binding.root) {
                        if (size < 8) {
                            insertTextViews(listOfStrings)
                        }
                    }
                    listeners()
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        (activity as MainActivity).apply {
            hideToolBar()
        }
    }

    override fun listeners() {

        binding.searchButton.setOnClickListener {
            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToSearchRecommendationsFragment())
        }

        for (each in binding.root) {
            if (each is TextView) {
                if (each != binding.root[1]) {
                    each.setOnClickListener(this)
                }
            }
        }

    }

    override fun onClick(v: View?) {
        if (v is TextView) {
            findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToSearchRecommendationsFragment(
                    v.text.toString()
                )
            )
        }
    }

}