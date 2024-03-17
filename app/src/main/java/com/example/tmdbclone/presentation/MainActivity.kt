package com.example.tmdbclone.presentation

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tmdbclone.R
import com.example.tmdbclone.databinding.ActivityMainBinding
import com.example.tmdbclone.domain.SessionManager
import com.example.tmdbclone.presentation.celebrities.CelebritiesViewModel
import com.example.tmdbclone.presentation.movies.MoviesViewModel
import com.example.tmdbclone.presentation.tvShows.TvShowsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    @Inject
    lateinit var sessionManager: SessionManager

    private val celebritiesViewModel: CelebritiesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        window.statusBarColor = getColor(R.color.app_darker)
        setSupportActionBar(binding.toolBar)
        setContentView(binding.root)

        initSession()
        setupBottomNavBar()
        initViewModels()

    }

    private fun initSession() {
        lifecycleScope.launch {
            sessionManager.authorize()
        }
    }

    private fun initViewModels() {
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
                    navController.navigate(R.id.moviesFragment)
                    showViews()
                    true
                }

                R.id.tvShowsFragment -> {
                    navController.navigate(R.id.tvShowsFragment)
                    showViews()
                    true
                }

                R.id.celebritiesFragment -> {
                    navController.navigate(R.id.celebritiesFragment)
                    showViews()
                    true
                }

                R.id.searchFragment -> {
                    navController.navigate(R.id.searchFragment)
                    showViews()
                    true
                }

                R.id.TMDBFragment -> {
                    navController.navigate(R.id.TMDBFragment)
                    binding.toolBar.visibility = View.GONE
                    binding.bottomNavView.visibility = View.VISIBLE
                    true
                }

                else -> false
            }
        }

        // This prevents from adding new instance of already chosen fragment // todo delete comment
        bottomNavigation.setOnItemReselectedListener {
            when (it.itemId) {
                R.id.moviesFragment -> {
                    if (it.itemId == bottomNavigation.menu.findItem(R.id.moviesFragment).itemId) {
                        navController.popBackStack(R.id.moviesFragment, false)
                    }
                    binding.toolBar.visibility = View.VISIBLE
                }
            }
        }

        // Changing Selected Menu Item
        navController.addOnDestinationChangedListener { _, destination, _ ->
            bottomNavigation.menu.findItem(destination.id)?.isChecked = true
        }

    }

    override fun navigateUpTo(upIntent: Intent?): Boolean {
        return navController.navigateUp()
    }

    private fun showViews() {
        binding.toolBar.visibility = View.VISIBLE
        supportActionBar?.show()
        binding.bottomNavView.visibility = View.VISIBLE
    }

    fun setToolBarTitle(title: String) {
        supportActionBar?.title = title
    }

    fun hideToolBar() {
        supportActionBar?.hide()
    }

    fun showToolBar() {
        supportActionBar?.show()
    }

    fun hideBottomNavigation() {
        binding.bottomNavView.visibility = View.GONE
    }

    fun showBottomNavigation() {
        binding.bottomNavView.visibility = View.VISIBLE
    }

}