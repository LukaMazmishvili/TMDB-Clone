package com.example.tmdbclone.presentation.auth

import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.tmdbclone.R
import com.example.tmdbclone.base.BaseFragment
import com.example.tmdbclone.databinding.FragmentLoginBinding
import com.example.tmdbclone.domain.SessionManager
import com.example.tmdbclone.presentation.MainActivityListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val authViewModel: AuthViewModel by activityViewModels()

    @Inject
    lateinit var sessionManager: SessionManager

    override fun started() {
        val mainActivityListener = activity as MainActivityListener
        mainActivityListener.hideBottomNavigation()
    }

    override fun listeners() {
        with(binding) {
            btnSignIn.setOnClickListener {
                val username: String = tietUserName.text.toString().trim()
                val password: String = tietPassword.text.toString().trim()
                lifecycleScope.launch {
                    sessionManager.saveFirstTimeFlag(false)
                }
                loginUser(username, password)

            }

            btnSignUp.setOnClickListener {
                lifecycleScope.launch {
                    sessionManager.saveFirstTimeFlag(false)
                }
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegistrationFragment())
            }
        }
    }

    private fun loginUser(username: String, password: String) {
        authViewModel.login(username, password)
    }

    override fun observer() {
        lifecycleScope.launch {
            sessionManager.isFirstTime.collect { isFirstTime ->
                if (isFirstTime) {
                    binding.btnContinue.visibility = View.VISIBLE
                    binding.linearLayout.visibility = View.VISIBLE
                    binding.btnContinue.setOnClickListener {
                        lifecycleScope.launch {
                            sessionManager.saveFirstTimeFlag(false)
                        }
                        findNavController().navigate(LoginFragmentDirections.actionGlobalMoviesFragment())
                        findNavController().popBackStack()
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                authViewModel.loginState.collect { token ->
//                    if (token.isNotEmpty())
//                        findNavController().popBackStack()
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                authViewModel.validationState.collect { result ->
                    with(binding) {
                        when (result.error) {
                            R.string.invalid_password_or_username -> {
                                tietPassword.error =
                                    getString(R.string.invalid_password_or_username)
                                tietUserName.error =
                                    getString(R.string.invalid_password_or_username)
                            }

                            R.string.invalid_password -> {
                                tietPassword.error = getString(R.string.invalid_password)
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