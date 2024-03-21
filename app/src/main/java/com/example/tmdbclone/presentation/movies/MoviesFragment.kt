package com.example.tmdbclone.presentation.movies

import android.os.Build
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.tmdbclone.R
import com.example.tmdbclone.base.BaseFragment
import com.example.tmdbclone.base.BaseViewModel
import com.example.tmdbclone.databinding.FragmentMoviesBinding
import com.example.tmdbclone.network.ConnectivityObserver
import com.example.tmdbclone.network.NetworkConnectivityObserver
import com.example.tmdbclone.presentation.MainActivity
import com.example.tmdbclone.presentation.adapters.GridAdapter
import com.example.tmdbclone.presentation.adapters.PopularAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding>(FragmentMoviesBinding::inflate) {

    private val viewModel: MoviesViewModel by activityViewModels()

    @Inject
    lateinit var networkConnectivityObserver: ConnectivityObserver

    private val adapterPopular by lazy {
        PopularAdapter(0)
    }

    private val adapterPIT by lazy {
        PopularAdapter(1)
    }

    private val adapterTrending by lazy {
        PopularAdapter(0)
    }

    private val adapterTopRated by lazy {
        GridAdapter()
    }

    private val adapterUpcoming by lazy {
        PopularAdapter(0)
    }

    override fun started() {
        setupViews()
        networkObserver(networkConnectivityObserver, viewModel)
    }

    override fun listeners() {
        adapterPopular.onItemClickedListener = {
            navigateToDetails(it.id!!, it.title ?: it.originalTitle!!)
        }
        adapterPIT.onItemClickedListener = {
            navigateToDetails(it.id!!, it.title ?: it.originalTitle!!)
        }
        adapterTrending.onItemClickedListener = {
            navigateToDetails(it.id!!, it.title ?: it.originalTitle!!)
        }
        adapterTopRated.onItemClickedListener = {
            navigateToDetails(it.id!!, it.title ?: it.originalTitle!!)
        }
        adapterUpcoming.onItemClickedListener = {
            navigateToDetails(it.id!!, it.title ?: it.originalTitle!!)
        }

        with(binding) {
            trvPopularMovies.setSeeAllButtonClickListener {
                findNavController().navigate(
                    MoviesFragmentDirections.actionMoviesFragmentToSeeAllFragment(
                        "Popular"
                    )
                )
            }

            trvPlayingInTheater.setSeeAllButtonClickListener {
                findNavController().navigate(
                    MoviesFragmentDirections.actionMoviesFragmentToSeeAllFragment(
                        "Playing In Theater"
                    )
                )
            }

            trvTrendingMovies.setSeeAllButtonClickListener {
                findNavController().navigate(
                    MoviesFragmentDirections.actionMoviesFragmentToSeeAllFragment(
                        "Trending"
                    )
                )
            }

            trvTopRatedMovies.setSeeAllButtonClickListener {
                findNavController().navigate(
                    MoviesFragmentDirections.actionMoviesFragmentToSeeAllFragment(
                        "Top Rated"
                    )
                )
            }

            trvUpcomingMovies.setSeeAllButtonClickListener {
                findNavController().navigate(
                    MoviesFragmentDirections.actionMoviesFragmentToSeeAllFragment(
                        "Upcoming"
                    )
                )
            }
        }
    }

    private fun setupViews() {
        with(binding) {

            // Popular Movies Recycler
            trvPopularMovies.setTitle("Popular")
            trvPopularMovies.setRecyclerViewAdapter(adapterPopular)

            // Playing In Theater Recycler
            trvPlayingInTheater.setTitle("Playing In Theater")
            trvPlayingInTheater.setRecyclerViewAdapter(adapterPIT)

            // Trending Movies Recycler
            trvTrendingMovies.setTitle("Trending")
            trvTrendingMovies.setRecyclerViewAdapter(adapterTrending)

            // Trending Movies Recycler
            trvTopRatedMovies.setTitle("Top Rated")
            trvTopRatedMovies.setGridRecyclerAdapter(adapterTopRated, 4)

            // Trending Movies Recycler
            trvUpcomingMovies.setTitle("Upcoming")
            trvUpcomingMovies.setRecyclerViewAdapter(adapterUpcoming)

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        (activity as MainActivity).apply {
            setToolBarTitle("Movies")
            showToolBar()
            showBottomNavigation()
        }
    }

    override fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.moviesState.collect { list ->
                    adapterPopular.submitList(list)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.playingNowMoviesState.collect { list ->
                    adapterPIT.submitList(list)

                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.trendingMoviesState.collect { list ->
                    adapterTrending.submitList(list)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.topRatedMoviesState.collect { list ->
                    adapterTopRated.submitList(list)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.upcomingMoviesState.collect { list ->
                    adapterUpcoming.submitList(list)
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
                    if (!isLoading) {
                        delay(3000)
                        binding.shimmer.visibility = View.INVISIBLE
                        binding.dataLayout.visibility = View.VISIBLE
                    } else {
                        binding.shimmer.visibility = View.VISIBLE
                        binding.dataLayout.visibility = View.INVISIBLE
                    }
                }
            }
        }
    }

    private fun navigateToDetails(movieId: Int, movieTitle: String) {
        activity?.supportFragmentManager
        findNavController().navigate(
            MoviesFragmentDirections.actionGlobalMovieDetailFragment(
                movieTitle,
                "Movie",
                movieId
            )
        )
    }

}