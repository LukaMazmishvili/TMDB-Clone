package com.example.tmdbclone.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbclone.domain.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authUseCase: AuthUseCase) : ViewModel() {

    // todo change type
    private val _authState = MutableStateFlow<Boolean>(false)
    private val authState = _authState.asStateFlow()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            authUseCase.execute(username, password)
        }
    }

    fun register(username: String, password: String, email: String) {
        viewModelScope.launch {
            authUseCase.execute(username, password, email)
        }
    }

}