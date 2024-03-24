package com.example.tmdbclone.presentation

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.MainThread
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import com.example.tmdbclone.R
import com.example.tmdbclone.databinding.ActivityMainBinding
import com.example.tmdbclone.domain.SessionManager
import com.example.tmdbclone.domain.repository.MoviesRepository
import com.example.tmdbclone.network.ConnectivityObserver
import com.example.tmdbclone.network.ConnectivityObserver.Status.Available
import com.example.tmdbclone.network.ConnectivityObserver.Status.Losing
import com.example.tmdbclone.network.ConnectivityObserver.Status.Lost
import com.example.tmdbclone.network.ConnectivityObserver.Status.Unavailable
import com.example.tmdbclone.presentation.celebrities.CelebritiesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainActivityListener {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    private lateinit var navController: NavController
    private lateinit var navGraph: NavGraph

    @Inject
    lateinit var sessionManager: SessionManager

    @Inject
    lateinit var connectivityManager: ConnectivityManager

    @Inject
    lateinit var connectivityObserver: ConnectivityObserver

    @Inject
    lateinit var moviesRepository: MoviesRepository

    private val celebritiesViewModel: CelebritiesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        window.statusBarColor = getColor(R.color.app_darker)
        setSupportActionBar(binding.toolBar)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
        navController = navHostFragment.navController
        navGraph = navController.navInflater.inflate(R.navigation.nav_graph)

        initSession()

        navController.graph = navGraph

        setContentView(binding.root)

        networkObserver()
        setupBottomNavBar()
        initViewModels()

    }

    private fun getGenres() {
        lifecycleScope.launch {
            moviesRepository.genres()
        }
    }

    private fun networkObserver() {
        if (!isInternetAvailable()) {
            navController.navigate(R.id.action_global_noNetworkFragment)
        } else {
            getGenres()
        }

        lifecycleScope.launch {
            connectivityObserver.observe().collect { networkStatus ->
                when (networkStatus) {
                    Lost, Unavailable, Losing -> {
                        viewModel.setNetworkStatus(false)
                    }

                    Available -> {
                        viewModel.setNetworkStatus(true)
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                connectivityObserver.observe().collect { networkStatus ->
                    when (networkStatus) {
                        ConnectivityObserver.Status.Lost, ConnectivityObserver.Status.Unavailable, ConnectivityObserver.Status.Losing -> {
                            viewModel.setNetworkStatus(false)
                            navController.navigate(R.id.noNetworkFragment)
                        }

                        ConnectivityObserver.Status.Available -> {
                            viewModel.setNetworkStatus(true)
                            navController.popBackStack()
                        }
                    }
                }
            }
        }
    }

    private fun isInternetAvailable(): Boolean {
        val activeNetwork = connectivityManager.activeNetwork

        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)

        return networkCapabilities != null && (networkCapabilities.hasTransport(
            NetworkCapabilities.TRANSPORT_WIFI
        ) || networkCapabilities.hasTransport(
            NetworkCapabilities.TRANSPORT_CELLULAR
        ))
    }

    @MainThread
    private fun initSession() {
        lifecycleScope.launch {
            sessionManager.authorize()
        }

        lifecycleScope.launch(Dispatchers.Main) {
            sessionManager.isFirstTime.collect { isFirstTime ->
                if (isFirstTime) {
                    navGraph.setStartDestination(R.id.loginFragment)
                    navController.navigate(R.id.loginFragment)
                } else {
                    navGraph.setStartDestination(R.id.moviesFragment)
                    navController.navigate(R.id.moviesFragment)
                }
            }
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

        val bottomNavigation = binding.bottomNavView

        bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.moviesFragment -> {
                    if (navController.currentDestination?.id != R.id.loginFragment) {
                        navController.navigate(R.id.moviesFragment)
                        showViews()
                        true
                    } else {
                        false
                    }
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

    private fun showViews() {
        binding.toolBar.visibility = View.VISIBLE
        supportActionBar?.show()
        binding.bottomNavView.visibility = View.VISIBLE
    }

    override fun setToolBarTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun hideToolBar() {
        supportActionBar?.hide()
    }

    override fun showToolBar() {
        supportActionBar?.show()
    }

    override fun hideBottomNavigation() {
        binding.bottomNavView.visibility = View.GONE
    }

    override fun showBottomNavigation() {
        binding.bottomNavView.visibility = View.VISIBLE
    }

}