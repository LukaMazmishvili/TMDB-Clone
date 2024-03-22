package com.example.tmdbclone.presentation.auth

import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.tmdbclone.R
import com.example.tmdbclone.base.BaseFragment
import com.example.tmdbclone.databinding.FragmentRegistrationBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegistrationFragment :
    BaseFragment<FragmentRegistrationBinding>(FragmentRegistrationBinding::inflate) {

    private val authViewModel: AuthViewModel by activityViewModels()

    override fun started() {

    }

    override fun listeners() {
        with(binding) {
            btnRegister.setOnClickListener {
                val username: String = tietUserName.text.toString().trim()
                val email: String = tietEmail.text.toString().trim()
                val password: String = tietPassword.text.toString().trim()

                registerUser(username, email, password)

            }
        }
    }

    private fun registerUser(username: String, email: String, password: String) {
        authViewModel.register(username, email, password)
    }

    override fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                authViewModel.registerState.collect { state ->
                    state.let {
                        findNavController().popBackStack()
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                authViewModel.validationState.collect { result ->
                    with(binding) {
                        when (result.error) {
                            R.string.invalid_password_or_username -> {
                                tilPassword.error =
                                    getString(R.string.invalid_password_or_username)
                                tilUsername.error =
                                    getString(R.string.invalid_password_or_username)
                            }

                            R.string.invalid_password -> {
                                tilPassword.error = getString(R.string.invalid_password)
                            }

                            R.string.invalid_email -> {
                                tilEmail.error = getString(R.string.invalid_email)
                            }
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                authViewModel.errorMsg.collect { errorMsg ->
                    if (errorMsg.isNotEmpty())
                        Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}