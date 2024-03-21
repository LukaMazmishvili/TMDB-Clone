package com.example.tmdbclone.presentation.movies

import androidx.lifecycle.viewModelScope
import com.example.tmdbclone.base.BaseViewModel
import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.MoviesDTO
import com.example.tmdbclone.domain.model.MovieModel
import com.example.tmdbclone.domain.usecase.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val getMoviesUseCase: GetMoviesUseCase) :
    BaseViewModel() {

    private val _moviesState = MutableStateFlow<List<MovieModel.Movie>>(emptyList())
    val moviesState = _moviesState.asStateFlow()

    private val _playingNowMoviesState =
        MutableStateFlow<List<MovieModel.Movie>>(emptyList())
    val playingNowMoviesState = _playingNowMoviesState.asStateFlow()

    private val _trendingMoviesState =
        MutableStateFlow<List<MovieModel.Movie>>(emptyList())
    val trendingMoviesState = _trendingMoviesState.asStateFlow()

    private val _topRatedMoviesState =
        MutableStateFlow<List<MovieModel.Movie>>(emptyList())
    val topRatedMoviesState = _topRatedMoviesState.asStateFlow()

    private val _upcomingMoviesState =
        MutableStateFlow<List<MovieModel.Movie>>(emptyList())
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
            networkStatus.collect { networkStatus ->
                networkStatus?.let {
                    if (networkStatus) {
                        getMoviesUseCase.getPopularMovies().collect { response ->
                            when (response) {
                                is Resource.Success -> {
                                    hideLoading()
                                    _moviesState.value = response.data!!.results!!
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
    }

    private fun fetchNowPlayingMovies() {
        viewModelScope.launch {
            networkStatus.collect { networkStatus ->
                networkStatus?.let {
                    if (networkStatus) {
                        getMoviesUseCase.getNowPlayingMovies().collect { response ->
                            when (response) {
                                is Resource.Success -> {
                                    hideLoading()
                                    _playingNowMoviesState.value = response.data!!.results!!
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
    }

    private fun fetchTrendingMovies() {
        viewModelScope.launch {
            networkStatus.collect { networkStatus ->
                networkStatus?.let {
                    if (networkStatus) {
                        getMoviesUseCase.getTrendingMovies().collect { response ->
                            when (response) {
                                is Resource.Success -> {
                                    hideLoading()
                                    _trendingMoviesState.value = response.data!!.results!!
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
    }

    private fun fetchTopRatedMovies() {
        viewModelScope.launch {
            networkStatus.collect { networkStatus ->
                networkStatus?.let {
                    if (networkStatus) {
                        getMoviesUseCase.getTopRatedMovies().collect { response ->
                            when (response) {
                                is Resource.Success -> {
                                    hideLoading()
                                    _topRatedMoviesState.value = response.data!!.results!!
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
    }

    private fun fetchUpcomingMovies() {
        viewModelScope.launch {
            networkStatus.collect { networkStatus ->
                networkStatus?.let {
                    if (networkStatus) {
                        getMoviesUseCase.getUpcomingMovies().collect { response ->
                            when (response) {
                                is Resource.Success -> {
                                    hideLoading()
                                    _upcomingMoviesState.value = response.data!!.results!!
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
    }

}