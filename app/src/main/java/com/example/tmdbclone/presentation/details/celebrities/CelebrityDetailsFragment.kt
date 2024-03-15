package com.example.tmdbclone.presentation.details.celebrities

import android.os.Bundle
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
import com.example.tmdbclone.databinding.FragmentCelebrityDetailsBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CelebrityDetailsFragment :
    BaseFragment<FragmentCelebrityDetailsBinding>(FragmentCelebrityDetailsBinding::inflate) {

    private val viewModel: CelebrityDetailsViewModel by viewModels()

    private val args: CelebrityDetailsFragmentArgs by navArgs()

    override fun started() {

    }

    override fun listeners() {

    }

    override fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.celebrityDetailsState.collect { details ->
                    details?.let {
                        // todo set data to the fragment
                    }
                }
            }
        }
    }

}