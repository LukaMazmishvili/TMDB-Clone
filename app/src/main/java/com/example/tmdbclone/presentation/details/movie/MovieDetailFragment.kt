package com.example.tmdbclone.presentation.details.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.tmdbclone.R
import com.example.tmdbclone.base.BaseFragment
import com.example.tmdbclone.common.Endpoints.IMAGE_BASE_URL
import com.example.tmdbclone.databinding.FragmentMovieDetailBinding
import com.example.tmdbclone.extension.uploadImage
import com.example.tmdbclone.extension.uploadImage350x450
import com.example.tmdbclone.extension.uploadImage750x450
import com.example.tmdbclone.presentation.adapters.CelebritiesAdapter
import com.example.tmdbclone.presentation.adapters.PopularAdapter
import com.example.tmdbclone.presentation.adapters.VideoAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailFragment :
    BaseFragment<FragmentMovieDetailBinding>(FragmentMovieDetailBinding::inflate) {

    private val viewModel: MovieDetailsViewModel by viewModels()
    private val args: MovieDetailFragmentArgs by navArgs()

    private val castAdapter by lazy {
        CelebritiesAdapter()
    }

    private val videosAdapter by lazy {
        VideoAdapter()
    }

    private val recommendedAdapter by lazy {
        PopularAdapter(0)
    }

    private val similarAdapter by lazy {
        PopularAdapter(0)
    }

    override fun started() {
        setupViews()
    }

    override fun listeners() {

    }

    private fun setupViews() {
        with(binding) {
            // Cast & Crew
            trvCast.setTitle("Cast & Crew")
            trvCast.setRecyclerViewAdapter(castAdapter)

            // Videos
            trvVideos.setTitle("Videos")
            trvVideos.setRecyclerViewAdapter(videosAdapter)

            // Recommended
            trvRecommended.setTitle("Recommended")
            trvRecommended.setRecyclerViewAdapter(recommendedAdapter)

            // Similar
            trvSimilar.setTitle("Similar")
            trvSimilar.setRecyclerViewAdapter(similarAdapter)
        }
    }

    override fun observer() {

        viewModel.movieIdState.value = args.movieId

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.moviesDetailsState.collect {
                    Log.d("MovieDetailsModelInFragment", "observer: $it")
                    when (it) {
                        null -> {
                            // nothing
                        }

                        else -> {
                            with(binding) {
                                rvRatings.setOverallRating(it.voteAverage!!)
                                rvRatings.fillStars(it.voteAverage)
                                rvRatings.setTotalVotes(it.voteCount!!)
                                it.posterPath?.let { it1 ->
                                    println(it1)
                                    ivMovieImage.uploadImage350x450(IMAGE_BASE_URL + it1)
                                }
                                it.backdropPath?.let { it1 ->
                                    println(it1)
                                    ivMovieCover.uploadImage750x450(IMAGE_BASE_URL + it1)
                                }
                                tvMovieDescription.text = it.overview
                                tvMovieTitle.text = it.title
                            }
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.moviesCastState.collect { list ->
                    when (list) {
                        null -> {}
                        else -> castAdapter.submitList(list.cast)
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.moviesVideosState.collect { list ->
                    list?.let {
                        videosAdapter.submitList(list.results!!)
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.moviesRecommendedState.collect { list ->
                    if (list != null) {
                        recommendedAdapter.submitList(list.results)
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.moviesSimilarState.collect { list ->
                    if (list != null) {
                        similarAdapter.submitList(list!!.results)
                    }
                }
            }
        }

    }

}