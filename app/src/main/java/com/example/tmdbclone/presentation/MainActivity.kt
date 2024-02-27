package com.example.tmdbclone.presentation

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.view.iterator
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tmdbclone.R
import com.example.tmdbclone.databinding.ActivityMainBinding
import com.example.tmdbclone.presentation.movies.MoviesFragment
import com.example.tmdbclone.presentation.search.SearchFragment
import com.example.tmdbclone.presentation.tvShows.TvShowsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import dagger.hilt.android.AndroidEntryPoint
import java.util.Stack

@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val fragmentStacks: MutableMap<Int, Stack<Fragment>> = mutableMapOf()

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        window.statusBarColor = getColor(R.color.app_dark)
        titleColor = getColor(R.color.app_green)
        setContentView(binding.root)

        setupBottomNavBar()

    }

    private fun setupBottomNavBar() {

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
        navController = navHostFragment.navController

        val bottomNavigation = binding.bottomNavView

//        bottomNavigation.setOnItemSelectedListener {
//            when (it.itemId) {
//                R.id.moviesFragment -> {
//                    navController.navigate(R.id.moviesFragment)
//                    true
//                }
//
//                R.id.tvShowsFragment -> {
//                    navController.navigate(R.id.tvShowsFragment)
//                    true
//                }
//
//                R.id.searchFragment -> {
//                    navController.navigate(R.id.searchFragment)
//                    true
//                }
//
//                else -> false
//            }
//        }

        setupWithNavController(bottomNavigation, navController)
    }

}