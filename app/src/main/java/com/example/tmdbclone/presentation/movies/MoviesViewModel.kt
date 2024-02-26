package com.example.tmdbclone.presentation.movies

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.tmdbclone.base.BaseViewModel
import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.PopularMovieDTO
import com.example.tmdbclone.domain.usecase.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val getMoviesUseCase: GetMoviesUseCase) :
    BaseViewModel() {

    private val _moviesState = MutableStateFlow<List<PopularMovieDTO.MovieModelDto>>(emptyList())
    val moviesState = _moviesState.asStateFlow()

    private val _playingNowMoviesState =
        MutableStateFlow<List<PopularMovieDTO.MovieModelDto>>(emptyList())
    val playingNowMoviesState = _playingNowMoviesState.asStateFlow()

    private val _trendingMoviesState =
        MutableStateFlow<List<PopularMovieDTO.MovieModelDto>>(emptyList())
    val trendingMoviesState = _trendingMoviesState.asStateFlow()

    private val _topRatedMoviesState =
        MutableStateFlow<List<PopularMovieDTO.MovieModelDto>>(emptyList())
    val topRatedMoviesState = _topRatedMoviesState.asStateFlow()

    init {
        fetchMovies()
        fetchNowPlayingMovies()
        fetchTrendingMovies()
        fetchTopRatedMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            getMoviesUseCase.invoke().collect { response ->
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

}