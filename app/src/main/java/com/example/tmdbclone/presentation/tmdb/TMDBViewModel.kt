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


    val isAuthorized = userUseCase.isAuthorized()

    init {
        getCurrentUser()
    }

    fun logOut() {
        viewModelScope.launch {
            userUseCase.logOut()
        }
    }

    fun getCurrentUser() = userUseCase.getCurrentUser()

}

