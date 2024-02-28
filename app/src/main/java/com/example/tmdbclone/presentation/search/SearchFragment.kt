package com.example.tmdbclone.presentation.search

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.tmdbclone.base.BaseFragment
import com.example.tmdbclone.databinding.FragmentSearchBinding
import com.example.tmdbclone.extension.insertTextViews
import com.example.tmdbclone.presentation.MainActivity
import com.example.tmdbclone.presentation.movies.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

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
                    binding.root.insertTextViews(listOfStrings)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        (activity as MainActivity).setToolBarTitle("Search")
    }

    override fun listeners() {

    }

}