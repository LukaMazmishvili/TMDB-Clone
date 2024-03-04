package com.example.tmdbclone.presentation.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.tmdbclone.R
import com.example.tmdbclone.base.BaseFragment
import com.example.tmdbclone.databinding.FragmentRegistrationBinding
import dagger.hilt.android.AndroidEntryPoint
import java.net.PasswordAuthentication

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

}