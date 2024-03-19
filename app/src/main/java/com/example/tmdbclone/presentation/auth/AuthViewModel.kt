package com.example.tmdbclone.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbclone.base.BaseViewModel
import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val userUseCase: UserUseCase) : BaseViewModel() {

    // todo change type
    private val _authState = MutableSharedFlow<String>()
    val authState = _authState.asSharedFlow()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            userUseCase.execute(username, password).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        hideLoading()
                        _authState.emit(response.data!!)
                    }

                    is Resource.Error -> {
                        hideLoading()
                        setErrorMsg(response.errorMsg)
                    }

                    is Resource.Loading -> showLoading()
                }
            }
        }
    }

    fun register(username: String, email: String, password: String) {
        viewModelScope.launch {
            userUseCase.execute(username, email, password)
        }
    }

}