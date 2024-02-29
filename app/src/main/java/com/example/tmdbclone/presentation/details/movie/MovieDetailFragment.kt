package com.example.tmdbclone.presentation.details.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.tmdbclone.R
import com.example.tmdbclone.base.BaseFragment
import com.example.tmdbclone.databinding.FragmentMovieDetailBinding

class MovieDetailFragment :
    BaseFragment<FragmentMovieDetailBinding>(FragmentMovieDetailBinding::inflate) {

    private val viewModel: MovieDetailsViewModel by viewModels()

    override fun started() {
        binding.rvRatings.setOverallRating(5.2f)
        binding.rvRatings.setTotalVotes(6413)
        binding.rvRatings.fillStars(5.2f)
    }

    override fun listeners() {

    }

}