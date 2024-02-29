package com.example.tmdbclone.presentation.details.movie

import androidx.lifecycle.viewModelScope
import com.example.tmdbclone.base.BaseViewModel
import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.CelebritiesModelDto
import com.example.tmdbclone.data.remote.model.MovieDetailsModelDto
import com.example.tmdbclone.data.remote.model.PopularMovieDTO
import com.example.tmdbclone.data.remote.model.VideoModelDto
import com.example.tmdbclone.domain.usecase.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val getMovieDetailsUseCase: GetMovieDetailsUseCase) :
    BaseViewModel() {

    private val _moviesDetailsState =
        MutableStateFlow<MovieDetailsModelDto?>(null)
    val moviesDetailsState = _moviesDetailsState.asStateFlow()

    private val _moviesCastState =
        MutableStateFlow<CelebritiesModelDto?>(null)
    val moviesCastState = _moviesCastState.asStateFlow()

    private val _moviesVideosState =
        MutableStateFlow<List<VideoModelDto>>(emptyList())
    val moviesVideosState = _moviesVideosState.asStateFlow()

    private val _moviesRecommendedState =
        MutableStateFlow<PopularMovieDTO?>(null)
    val moviesRecommendedState = _moviesRecommendedState.asStateFlow()

    private val _moviesSimilarState =
        MutableStateFlow<PopularMovieDTO?>(null)
    val moviesSimilarState = _moviesSimilarState.asStateFlow()

    init {
        fetchMovieDetails()
        fetchMovieCast()
        fetchMovieVideos()
        fetchMovieRecommended()
        fetchMovieSimilar()
    }

    private fun fetchMovieDetails() {
        viewModelScope.launch {
            getMovieDetailsUseCase.getMovieDetails().collect { response ->
                when (response) {
                    is Resource.Success -> {
                        hideLoading()
                        _moviesDetailsState.value = response.data!!
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

    private fun fetchMovieCast() {
        viewModelScope.launch {
            getMovieDetailsUseCase.getMovieCast().collect { response ->
                when (response) {
                    is Resource.Success -> {
                        hideLoading()
                        _moviesCastState.value = response.data!!
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

    private fun fetchMovieVideos() {
        viewModelScope.launch {
            getMovieDetailsUseCase.getMovieVideos().collect { response ->
                when (response) {
                    is Resource.Success -> {
                        hideLoading()
                        _moviesVideosState.value = response.data!!
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

    private fun fetchMovieRecommended() {
        viewModelScope.launch {
            getMovieDetailsUseCase.getMovieRecommended().collect { response ->
                when (response) {
                    is Resource.Success -> {
                        hideLoading()
                        _moviesRecommendedState.value = response.data!!
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

    private fun fetchMovieSimilar() {
        viewModelScope.launch {
            getMovieDetailsUseCase.getMovieSimilar().collect { response ->
                when (response) {
                    is Resource.Success -> {
                        hideLoading()
                        _moviesSimilarState.value = response.data!!
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