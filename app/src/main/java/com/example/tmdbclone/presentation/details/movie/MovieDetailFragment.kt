package com.example.tmdbclone.presentation.details.movie

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tmdbclone.R
import com.example.tmdbclone.base.BaseFragment
import com.example.tmdbclone.common.Endpoints.IMAGE_BASE_URL
import com.example.tmdbclone.databinding.FragmentMovieDetailBinding
import com.example.tmdbclone.extension.toMediaTypes
import com.example.tmdbclone.extension.uploadImage350x450
import com.example.tmdbclone.extension.uploadImage750x450
import com.example.tmdbclone.presentation.MainActivityListener
import com.example.tmdbclone.presentation.adapters.CelebritiesGridAdapter
import com.example.tmdbclone.presentation.adapters.GenresAdapter
import com.example.tmdbclone.presentation.adapters.MovieAdapter
import com.example.tmdbclone.presentation.adapters.VideoAdapter
import com.example.tmdbclone.presentation.movies.MoviesFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailFragment :
    BaseFragment<FragmentMovieDetailBinding>(FragmentMovieDetailBinding::inflate) {

    private val viewModel: MovieDetailsViewModel by viewModels()
    private val args: MovieDetailFragmentArgs by navArgs()

    private var isFavorite: Boolean = false

    private val genresAdapter by lazy {
        GenresAdapter()
    }

    private val castAdapter by lazy {
        CelebritiesGridAdapter()
    }

    private val videosAdapter by lazy {
        VideoAdapter()
    }

    private val recommendedAdapter by lazy {
        MovieAdapter(0)
    }

    private val similarAdapter by lazy {
        MovieAdapter(0)
    }

    override fun started() {
        val mainActivityListener = activity as MainActivityListener
        mainActivityListener.hideToolBar()
        setupViews()
    }

    override fun listeners() {
        videosAdapter.onItemClickedListener = {
            it.key?.let { it1 ->
                openVideo(it1)
            }
        }

        with(binding) {
            backButton.setOnClickListener {
                findNavController().popBackStack()
            }

            recommendedAdapter.onItemClickedListener = {
                navigateToDetails(
                    it.id!!,
                    it.title ?: it.originalName ?: it.originalTitle!!,
                    it.mediaType!!,
                )
            }
            similarAdapter.onItemClickedListener = {
                navigateToDetails(
                    it.id!!,
                    it.title ?: it.originalName ?: it.originalTitle!!,
                    it.mediaType ?: "Movie",
                )
            }

            trvCast.setSeeAllButtonClickListener {
                // todo
            }
            trvVideos.setSeeAllButtonClickListener {
                // todo
            }
            trvRecommended.setSeeAllButtonClickListener {
                // todo
            }
        }

    }

    private fun setupViews() {
        with(binding) {

            movieTitle.text = args.movieTitle

            // Genres
            rvGenres.adapter = genresAdapter

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

            heartIcon.setOnClickListener {
                if (!isFavorite) {
                    viewModel.addToFavourites(args.movieId)
                } else {
                    viewModel.removeFavourite(args.movieId)
                }
            }
        }
    }

    private fun openVideo(videoId: String) {
        val intent = Intent(Intent.ACTION_VIEW)

        intent.data = Uri.parse("https://www.youtube.com/watch?v=$videoId")
        intent.setPackage("com.google.android.youtube")

        // todo open link in web if youtube app is not installed
        startActivity(intent)
    }

    override fun observer() {

        viewModel.movieIdState.value = args.movieId
        viewModel.mediaTypeState.value = args.mediaType.toMediaTypes()
        viewModel.isFavourite(args.movieId)

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
                                it.posterPath.let { path ->
                                    ivMovieImage.uploadImage350x450(
                                        IMAGE_BASE_URL + path,
                                        placeHolder = R.drawable.ic_movies
                                    )
                                }
                                it.backdropPath?.let { path ->
                                    ivMovieCover.uploadImage750x450(
                                        IMAGE_BASE_URL + path,
                                        placeHolder = R.drawable.ic_movies
                                    )
                                }
                                tvMovieDescription.text = it.overview
                                tvMovieTitle.text = it.title ?: it.originalName
                                movieTitle.text = it.title ?: it.originalName
                                genresAdapter.submitList(it.genres)
                                it.belongsToCollection?.let { collection ->
                                    itemCollection.tvTitle.text = collection.name
                                    val genres = mutableListOf<String>()
                                    it.genres?.forEach { genre ->
                                        genres.add(genre?.name.toString())
                                    }
                                    itemCollection.tvGenres.text =
                                        genres.toString().replace("[", "").replace("]", "")
                                    it.posterPath?.let { backDropPath ->
                                        itemCollection.ivCollectionImage.uploadImage350x450(
                                            IMAGE_BASE_URL + backDropPath,
                                            placeHolder = R.drawable.ic_movies
                                        )
                                    }
                                } ?: run {
                                    itemCollectionWrapper.visibility = View.GONE
                                }
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

                        else -> {
                            if (!list.results.isNullOrEmpty()) {
                                castAdapter.submitList(list.cast)
                            } else {
                                binding.trvCast.visibility = View.GONE
                            }
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.moviesVideosState.collect { list ->
                    list?.let {
                        if (!list.results.isNullOrEmpty()) {
                            videosAdapter.submitList(list.results)
                        } else {
                            binding.trvVideos.visibility = View.GONE
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.moviesRecommendedState.collect { list ->
                    list?.let {
                        if (!list.results.isNullOrEmpty()) {
                            recommendedAdapter.submitList(list.results)
                        } else {
                            binding.trvRecommended.visibility = View.GONE
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.moviesSimilarState.collect { list ->
                    list?.let {
                        if (!list.results.isNullOrEmpty()) {
                            similarAdapter.submitList(list.results)
                        } else {
                            binding.trvSimilar.visibility = View.GONE
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.isFavouriteState.collect { isFavourite ->
                    if (isFavourite) {
                        isFavorite = true
                        binding.heartIcon.setImageResource(R.drawable.ic_favourite_full)
                    } else {
                        binding.heartIcon.setImageResource(R.drawable.ic_favourite)
                    }

                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.removeFavouriteState.collect { removed ->
                    if (removed) {
                        isFavorite = false
                        binding.heartIcon.setImageResource(R.drawable.ic_favourite)
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.isLoading.collect { isLoading ->
                    if (isLoading) {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.dataLayout.visibility = View.GONE
                    } else {
                        delay(1000)
                        binding.progressBar.visibility = View.GONE
                        binding.dataLayout.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun navigateToDetails(movieId: Int, movieTitle: String, mediaType: String) {
        findNavController().popBackStack()
        findNavController().navigate(
            MoviesFragmentDirections.actionGlobalMovieDetailFragment(
                movieTitle,
                mediaType,
                movieId
            )
        )
    }

}