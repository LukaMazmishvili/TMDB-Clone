package com.example.tmdbclone.presentation

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.R.attr.actionBarSize
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.tmdbclone.R
import com.example.tmdbclone.databinding.ActivityMainBinding
import com.example.tmdbclone.presentation.celebrities.CelebritiesViewModel
import com.example.tmdbclone.presentation.movies.MoviesViewModel
import com.example.tmdbclone.presentation.tvShows.TvShowsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    private val moviesViewModel: MoviesViewModel by viewModels()
    private val tvShowsViewModel: TvShowsViewModel by viewModels()
    private val celebritiesViewModel: CelebritiesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        window.statusBarColor = getColor(R.color.app_dark)
        setSupportActionBar(binding.toolBar)
        setContentView(binding.root)

        setupBottomNavBar()
        initViewModels()

    }

    private fun initViewModels() {

        // todo check if same happens in release build

//        lifecycleScope.launch(Dispatchers.IO) {
//            moviesViewModel.apply {
//                fetchMovies()
//                fetchNowPlayingMovies()
//                fetchTrendingMovies()
//                fetchTopRatedMovies()
//                fetchUpcomingMovies()
//            }
//        }

//        lifecycleScope.launch(Dispatchers.IO) {
//            tvShowsViewModel.apply {
//                fetchAiringTodayTvShows()
//                fetchTrendingTvShows()
//                fetchTopRatedTvShows()
//                fetchPopularTvShows()
//            }
//        }

        lifecycleScope.launch(Dispatchers.IO) {
            celebritiesViewModel.apply {
                getPopularCelebrities()
                getTrendingCelebrities()
            }
        }
    }

    private fun setupBottomNavBar() {

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
        navController = navHostFragment.navController

        val bottomNavigation = binding.bottomNavView

        bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.moviesFragment -> {
                    navController.navigate(R.id.action_global_moviesFragment)
                    binding.toolBar.visibility = View.VISIBLE
                    true
                }

                R.id.tvShowsFragment -> {
                    navController.navigate(R.id.tvShowsFragment)
                    binding.toolBar.visibility = View.VISIBLE
                    true
                }

                R.id.celebritiesFragment -> {
                    navController.navigate(R.id.celebritiesFragment)
                    binding.toolBar.visibility = View.VISIBLE
                    true
                }

                R.id.searchFragment -> {
                    navController.navigate(R.id.searchFragment)
                    binding.toolBar.visibility = View.VISIBLE
                    true
                }

                R.id.TMDBFragment -> {
                    navController.navigate(R.id.TMDBFragment)
                    binding.toolBar.visibility = View.GONE
                    true
                }

                else -> false
            }
        }

        // This prevents from adding new instance of already chosen fragment
        bottomNavigation.setOnItemReselectedListener { /* do absolutely nothing :DDDD */ }

        // Changing Selected Menu Item
        navController.addOnDestinationChangedListener { _, destination, _ ->
            bottomNavigation.menu.findItem(destination.id)?.isChecked = true
        }

    }

    fun setToolBarTitle(title: String) {
        supportActionBar?.title = title
    }

}