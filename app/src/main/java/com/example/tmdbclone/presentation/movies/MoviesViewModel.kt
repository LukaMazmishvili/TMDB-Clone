package com.example.tmdbclone.presentation.movies

import androidx.lifecycle.viewModelScope
import com.example.tmdbclone.base.BaseViewModel
import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.MoviesDTO
import com.example.tmdbclone.domain.usecase.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val getMoviesUseCase: GetMoviesUseCase) :
    BaseViewModel() {

    private val _moviesState = MutableStateFlow<List<MoviesDTO.MovieModelDto>>(emptyList())
    val moviesState = _moviesState.asStateFlow()

    private val _playingNowMoviesState =
        MutableStateFlow<List<MoviesDTO.MovieModelDto>>(emptyList())
    val playingNowMoviesState = _playingNowMoviesState.asStateFlow()

    private val _trendingMoviesState =
        MutableStateFlow<List<MoviesDTO.MovieModelDto>>(emptyList())
    val trendingMoviesState = _trendingMoviesState.asStateFlow()

    private val _topRatedMoviesState =
        MutableStateFlow<List<MoviesDTO.MovieModelDto>>(emptyList())
    val topRatedMoviesState = _topRatedMoviesState.asStateFlow()

    private val _upcomingMoviesState =
        MutableStateFlow<List<MoviesDTO.MovieModelDto>>(emptyList())
    val upcomingMoviesState = _upcomingMoviesState.asStateFlow()

    init {
        fetchMovies()
        fetchNowPlayingMovies()
        fetchTrendingMovies()
        fetchTopRatedMovies()
        fetchUpcomingMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            getMoviesUseCase.getPopularMovies().collect { response ->
                when (response) {
                    is Resource.Success -> {
                        hideLoading()
                        _moviesState.value = response.data!!
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

    private fun fetchNowPlayingMovies() {
        viewModelScope.launch {
            getMoviesUseCase.getNowPlayingMovies().collect { response ->
                when (response) {
                    is Resource.Success -> {
                        hideLoading()
                        _playingNowMoviesState.value = response.data!!
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

    private fun fetchTrendingMovies() {
        viewModelScope.launch {
            getMoviesUseCase.getTrendingMovies().collect { response ->
                when (response) {
                    is Resource.Success -> {
                        hideLoading()
                        _trendingMoviesState.value = response.data!!
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

    private fun fetchTopRatedMovies() {
        viewModelScope.launch {
            getMoviesUseCase.getTopRatedMovies().collect { response ->
                when (response) {
                    is Resource.Success -> {
                        hideLoading()
                        _topRatedMoviesState.value = response.data!!
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

    private fun fetchUpcomingMovies() {
        viewModelScope.launch {
            getMoviesUseCase.getUpcomingMovies().collect { response ->
                when (response) {
                    is Resource.Success -> {
                        hideLoading()
                        _upcomingMoviesState.value = response.data!!
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