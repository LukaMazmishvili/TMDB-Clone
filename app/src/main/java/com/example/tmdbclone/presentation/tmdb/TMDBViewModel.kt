package com.example.tmdbclone.presentation.tmdb

import androidx.lifecycle.viewModelScope
import com.example.tmdbclone.base.BaseViewModel
import com.example.tmdbclone.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TMDBViewModel @Inject constructor(private val userUseCase: UserUseCase) : BaseViewModel() {

    private val _isAuthorized = MutableStateFlow(false)
    val isAuthorized = _isAuthorized.asStateFlow()

    private val _currentUser = MutableStateFlow("")
    val currentUser = _currentUser.asStateFlow()

    init {
        getCurrentUser()
        isAuthorized()
    }

    fun logOut() {
        viewModelScope.launch {
            userUseCase.logOut()
        }
    }

    private fun getCurrentUser() {
        viewModelScope.launch {
            userUseCase.getCurrentUser().collect {
                _currentUser.value = it
            }
        }
    }

    private fun isAuthorized() {
        viewModelScope.launch {
            userUseCase.isAuthorized().collect {
                _isAuthorized.value = it
            }
        }
    }
}
