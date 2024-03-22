package com.example.tmdbclone.presentation.tmdb

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.tmdbclone.base.BaseFragment
import com.example.tmdbclone.databinding.FragmentTmdbBinding
import com.example.tmdbclone.presentation.MainActivity
import com.example.tmdbclone.presentation.MainActivityListener
import com.example.tmdbclone.presentation.ui.customViews.notAuthorizedDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TMDBFragment : BaseFragment<FragmentTmdbBinding>(FragmentTmdbBinding::inflate) {

    private val viewModel: TMDBViewModel by viewModels()


    override fun started() {
        val mainActivityListener = activity as MainActivityListener
        mainActivityListener.showBottomNavigation()
    }

    override fun listeners() {
        binding.btnLoginorRegister.setOnClickListener {
            findNavController().navigate(TMDBFragmentDirections.actionTMDBFragmentToLoginFragment())
        }

        binding.ivIcFav.setOnClickListener {
            lifecycleScope.launch {
                viewModel.isAuthorized.collect { isAuthorized ->
                    if (!isAuthorized) {
                        notAuthorizedDialog(requireContext())
                    }
                }
            }
        }

        binding.btnSignOut.setOnClickListener {
            viewModel.logOut()
            observer()
        }
    }

    override fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.getCurrentUser().collect { currentUser ->
                    when (currentUser) {
                        "Username" -> {
                            binding.btnLoginorRegister.visibility = View.VISIBLE
                            binding.btnSignOut.visibility = View.GONE
                            binding.tvUsername.text = currentUser
                        }

                        "" -> {
                            binding.btnLoginorRegister.visibility = View.VISIBLE
                            binding.btnSignOut.visibility = View.GONE
                            binding.tvUsername.text = "Username"
                        }

                        else -> {
                            binding.btnLoginorRegister.visibility = View.GONE
                            binding.btnSignOut.visibility = View.VISIBLE
                            binding.tvUsername.text = currentUser
                        }

                    }
                }
            }
        }
    }

}