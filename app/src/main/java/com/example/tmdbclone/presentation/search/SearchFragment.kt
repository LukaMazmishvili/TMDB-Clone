package com.example.tmdbclone.presentation.search

import android.os.Build
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.iterator
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
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

    private val searchViewModel: SearchViewModel by viewModels()

    private var query = MutableSharedFlow<String>()

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
        (activity as MainActivity).apply {
            hideToolBar()
        }
    }

    override fun listeners() {

        for (each in binding.root) {
            if (each is TextView) {
                each.setOnClickListener(this)
            }
        }

    }

    override fun onClick(v: View?) {
        if (v is TextView) {
//            query = v.text.toString()
//            started()
        }
    }

}