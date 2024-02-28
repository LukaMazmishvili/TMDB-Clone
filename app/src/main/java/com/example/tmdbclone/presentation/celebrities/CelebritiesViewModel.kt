package com.example.tmdbclone.presentation.celebrities

import androidx.lifecycle.viewModelScope
import com.example.tmdbclone.base.BaseViewModel
import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.CelebritiesModelDto
import com.example.tmdbclone.domain.usecase.GetCelebritiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CelebritiesViewModel @Inject constructor(private val getCelebritiesUseCase: GetCelebritiesUseCase) :
    BaseViewModel() {

    private val _popularCelebritiesState =
        MutableStateFlow<List<CelebritiesModelDto.Result>>(emptyList())
    val popularCelebritiesState = _popularCelebritiesState.asStateFlow()

    private val _trendingCelebritiesState =
        MutableStateFlow<List<CelebritiesModelDto.Result>>(emptyList())
    val trendingCelebritiesState = _trendingCelebritiesState.asStateFlow()

//    init {
//        getPopularCelebrities()
//        getTrendingCelebrities()
//    }

     fun getPopularCelebrities() {
        viewModelScope.launch {
            getCelebritiesUseCase.getPopularCelebrities().collect { response ->
                when (response) {
                    is Resource.Success -> {
                        hideLoading()
                        _popularCelebritiesState.value = response.data!!
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

     fun getTrendingCelebrities() {
        viewModelScope.launch {
            getCelebritiesUseCase.getTrendingCelebrities().collect { response ->
                when (response) {
                    is Resource.Success -> {
                        hideLoading()
                        _trendingCelebritiesState.value = response.data!!
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
}