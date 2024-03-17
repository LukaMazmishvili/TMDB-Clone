package com.example.tmdbclone.presentation.details.celebrities

import androidx.lifecycle.viewModelScope
import com.example.tmdbclone.base.BaseViewModel
import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.CelebrityDetailsModelDto
import com.example.tmdbclone.data.remote.model.MoviesDTO
import com.example.tmdbclone.domain.usecase.GetCelebrityDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CelebrityDetailsViewModel @Inject constructor(private val celebrityDetailsUseCase: GetCelebrityDetailsUseCase) :
    BaseViewModel() {


    val personIdState = MutableStateFlow<Int>(-1)

    private val _celebrityDetailsState = MutableStateFlow<CelebrityDetailsModelDto?>(null)
    val celebrityDetailsState = _celebrityDetailsState.asStateFlow()

    private val _celebrityMovieCreditsState = MutableStateFlow<List<MoviesDTO>>(emptyList())
    val celebrityMovieCreditsState = _celebrityMovieCreditsState.asStateFlow()

    private val _celebrityTvShowsCreditsState = MutableStateFlow<List<MoviesDTO>>(emptyList())
    val celebrityTvShowsCreditsState = _celebrityTvShowsCreditsState.asStateFlow()

    init {
        fetchCelebrityDetails()
        fetchCelebrityMovieCredits()
        fetchCelebrityTvShowCredits()
    }

    private fun fetchCelebrityDetails() {
        viewModelScope.launch {
            personIdState.collect { personId ->
                celebrityDetailsUseCase.getCelebrityDetails(personId).collect { response ->
                    when (response) {
                        is Resource.Success -> {
                            hideLoading()
                            _celebrityDetailsState.value = response.data!!
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

    private fun fetchCelebrityMovieCredits() {
        viewModelScope.launch {
            personIdState.collect { personId ->
                celebrityDetailsUseCase.getCelebrityMovieCredits(personId).collect { response ->
                    when (response) {
                        is Resource.Success -> {
                            hideLoading()
                            _celebrityMovieCreditsState.value = response.data!!
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

    private fun fetchCelebrityTvShowCredits() {
        viewModelScope.launch {
            personIdState.collect { personId ->
                celebrityDetailsUseCase.getCelebrityTvCredits(personId).collect { response ->
                    when (response) {
                        is Resource.Success -> {
                            hideLoading()
                            _celebrityTvShowsCreditsState.value = response.data!!
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

}