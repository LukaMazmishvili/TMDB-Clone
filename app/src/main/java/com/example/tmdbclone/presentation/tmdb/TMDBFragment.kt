package com.example.tmdbclone.presentation.tmdb

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.example.tmdbclone.base.BaseFragment
import com.example.tmdbclone.databinding.FragmentTmdbBinding
import com.example.tmdbclone.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TMDBFragment : BaseFragment<FragmentTmdbBinding>(FragmentTmdbBinding::inflate) {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun started() {
        (activity as MainActivity).showBottomNavigation()
    }

    override fun listeners() {
        binding.btnLoginorRegister.setOnClickListener {
            findNavController().navigate(TMDBFragmentDirections.actionTMDBFragmentToLoginFragment())
        }
    }

}