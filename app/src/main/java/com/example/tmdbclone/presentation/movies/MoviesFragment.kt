package com.example.tmdbclone.presentation.movies

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.example.tmdbclone.base.BaseFragment
import com.example.tmdbclone.common.Endpoints
import com.example.tmdbclone.data.remote.model.PopularMovieDTO
import com.example.tmdbclone.databinding.FragmentMoviesBinding
import com.example.tmdbclone.presentation.movies.adapters.PopularAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding>(FragmentMoviesBinding::inflate) {

    private val viewModel: MoviesViewModel by viewModels()

    private val adapter by lazy {
        PopularAdapter()
    }

    override fun started() {
        setupViews()
    }

    override fun listeners() {

    }

    private fun setupViews() {
        with(binding) {
            trvPopularMovies.setTitle("Popular")
            trvPopularMovies.setRecyclerViewAdapter(adapter)

            trvPlaintInTheater.setTitle("Playing In Theater")
        }
    }

    override fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.moviesState.collect { list ->
                    adapter.submitList(list)
                }
            }

            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.errorMsg.collect { errorMsg ->
                    Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_SHORT).show()
                }
            }

            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.isLoading.collect { isLoading ->
                    Log.d("isLoading", "observer: $isLoading")
                }
            }
        }
    }

}