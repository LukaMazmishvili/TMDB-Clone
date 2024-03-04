package com.example.tmdbclone.presentation.auth

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tmdbclone.base.BaseFragment
import com.example.tmdbclone.databinding.FragmentLoginBinding
import com.example.tmdbclone.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val authViewModel: AuthViewModel by activityViewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun started() {
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

    }

}