package com.example.tmdbclone.presentation.auth

import android.app.AlertDialog
import android.app.Dialog
import android.os.Build
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.tmdbclone.base.BaseFragment
import com.example.tmdbclone.databinding.FragmentLoginBinding
import com.example.tmdbclone.domain.SessionManager
import com.example.tmdbclone.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val authViewModel: AuthViewModel by activityViewModels()

    @Inject
    lateinit var sessionManager: SessionManager

    @RequiresApi(Build.VERSION_CODES.O)
    override fun started() {

        lifecycleScope.launch {
            sessionManager.isFirstTime.collect { isFirstTime ->
                if (isFirstTime) {
                    binding.btnContinue.visibility = View.VISIBLE
                    binding.linearLayout.visibility = View.VISIBLE
                    binding.btnContinue.setOnClickListener {
                        lifecycleScope.launch(Dispatchers.IO) {
                            sessionManager.saveFirstTimeFlag(false)
                        }
                        findNavController().navigate(LoginFragmentDirections.actionGlobalMoviesFragment())
                        findNavController().popBackStack()
                    }
                }
            }
        }

        (activity as MainActivity).hideBottomNavigation()
    }

    override fun listeners() {
        with(binding) {
            btnSignIn.setOnClickListener {
                val username: String = tietUserName.text.toString().trim()
                val password: String = tietPassword.text.toString().trim()

                loginUser(username, password)

            }



            btnSignUp.setOnClickListener {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegistrationFragment())
            }
        }
    }

    private fun loginUser(username: String, password: String) {
        authViewModel.login(username, password)
    }

    override fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                authViewModel.authState.collect { token ->
                    findNavController().popBackStack()
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