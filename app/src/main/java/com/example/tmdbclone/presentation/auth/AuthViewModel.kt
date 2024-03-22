package com.example.tmdbclone.presentation.auth

import androidx.lifecycle.viewModelScope
import com.example.tmdbclone.base.BaseViewModel
import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.common.ValidationResult
import com.example.tmdbclone.data.remote.model.UserModel
import com.example.tmdbclone.domain.usecase.UserUseCase
import com.example.tmdbclone.domain.usecase.ValidationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val validationUseCase: ValidationUseCase
) : BaseViewModel() {

    private val _loginState = MutableSharedFlow<String>()
    val loginState = _loginState.asSharedFlow()

    private val _registerState = MutableSharedFlow<UserModel?>()
    val registerState = _registerState.asSharedFlow()

    private val _validationState = MutableSharedFlow<ValidationResult>()
    val validationState = _validationState.asSharedFlow()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val validation = validationUseCase.validate(username, password)
            if (validation.success) {
                userUseCase.execute(username, password).collect { response ->
                    when (response) {
                        is Resource.Success -> {
                            hideLoading()
                            _loginState.emit(response.data!!)
                        }

                        is Resource.Error -> {
                            hideLoading()
                            setErrorMsg(response.errorMsg)
                        }

                        is Resource.Loading -> showLoading()
                    }
                }
            } else {
                _validationState.emit(validation)
            }
        }
    }

    fun register(username: String, email: String, password: String) {
        viewModelScope.launch {
            val validation = validationUseCase.validate(username, email, password)
            if (validation.success) {
                userUseCase.execute(username, email, password).collect { response ->
                    when (response) {
                        is Resource.Success -> {
                            hideLoading()
                            _registerState.emit(response.data!!)
                        }

                        is Resource.Error -> {
                            hideLoading()
                            setErrorMsg(response.errorMsg)
                        }

                        is Resource.Loading -> showLoading()
                    }
                }
            } else {
                _validationState.emit(validation)
            }
        }
    }

}