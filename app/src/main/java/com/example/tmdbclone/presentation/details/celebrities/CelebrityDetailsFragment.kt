package com.example.tmdbclone.presentation.details.celebrities

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tmdbclone.R
import com.example.tmdbclone.base.BaseFragment
import com.example.tmdbclone.common.Endpoints.IMAGE_BASE_URL
import com.example.tmdbclone.databinding.FragmentCelebrityDetailsBinding
import com.example.tmdbclone.extension.uploadImage200x300
import com.example.tmdbclone.extension.uploadImage350x450
import com.example.tmdbclone.presentation.MainActivity
import com.example.tmdbclone.presentation.MainActivityListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CelebrityDetailsFragment :
    BaseFragment<FragmentCelebrityDetailsBinding>(FragmentCelebrityDetailsBinding::inflate) {

    private val viewModel: CelebrityDetailsViewModel by viewModels()

    private val args: CelebrityDetailsFragmentArgs by navArgs()

    override fun started() {
        val mainActivityListener = activity as MainActivityListener
        mainActivityListener.hideToolBar()
        binding.celebrityName.text = args.celebrityName
        viewModel.personIdState.value = args.celebrityId

    }

    override fun listeners() {
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.celebrityDetailsState.collect { details ->
                    details?.let { model ->
                        with(binding) {
                            model.profilePath?.let { posterPath ->
                                ivCelebImage.uploadImage350x450(IMAGE_BASE_URL + posterPath, true)
                            }
                            tvCelebTitle.text = model.name
                            model.birthday?.let { birthday ->
                                llDateOfBirth.visibility = View.VISIBLE
                                tvDateOfBirth.text = birthday
                            } ?: run {
                                llDateOfBirth.visibility = View.GONE
                            }
                            model.placeOfBirth?.let { placeOfBirth ->
                                llBirthplace.visibility = View.VISIBLE
                                tvBirthplace.text = placeOfBirth
                            } ?: run {
                                llBirthplace.visibility = View.GONE
                            }
                            model.knownForDepartment?.let { knownForDepartment ->
                                llKnownFor.visibility = View.VISIBLE
                                tvKnownFor.text = knownForDepartment
                            } ?: run {
                                llKnownFor.visibility = View.GONE
                            }
                            model.deathDay?.let { deathDay ->
                                llDeathDay.visibility = View.VISIBLE
                                tvDeathDay.text = deathDay
                            } ?: run {
                                llDeathDay.visibility = View.GONE
                            }
                            tvDescription.text = model.biography
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.isLoading.collect { isLoading ->
                    if (isLoading) {
                        binding.dataLayout.visibility = View.GONE
                        binding.progressBar.visibility = View.VISIBLE
                    } else {
                        delay(200)
                        binding.dataLayout.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }
        }
    }

}