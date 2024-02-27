package com.example.tmdbclone.presentation.tvShows

import androidx.lifecycle.viewModelScope
import com.example.tmdbclone.base.BaseViewModel
import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.PopularMovieDTO
import com.example.tmdbclone.domain.usecase.GetTvShowsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowsViewModel @Inject constructor(private val getTvShowsUseCase: GetTvShowsUseCase) :
    BaseViewModel() {

    private val _airingTodayTvShowsState =
        MutableStateFlow<List<PopularMovieDTO.MovieModelDto>>(emptyList())
    val airingTodayTvShowsState = _airingTodayTvShowsState.asStateFlow()

    private val _trendingTvShowsState =
        MutableStateFlow<List<PopularMovieDTO.MovieModelDto>>(emptyList())
    val trendingTvShowsState = _trendingTvShowsState.asStateFlow()

    private val _topRatedTvShowsState =
        MutableStateFlow<List<PopularMovieDTO.MovieModelDto>>(emptyList())
    val topRatedTvShowsState = _topRatedTvShowsState.asStateFlow()

    private val _popularTvShowsState =
        MutableStateFlow<List<PopularMovieDTO.MovieModelDto>>(emptyList())
    val popularTvShowsState = _popularTvShowsState.asStateFlow()

    init {
        fetchAiringTodayTvShows()
        fetchTrendingTvShows()
        fetchTopRatedTvShows()
        fetchPopularTvShows()
    }

    private fun fetchAiringTodayTvShows() {
        viewModelScope.launch {
            getTvShowsUseCase.getAiringTodayTvShows().collect { response ->
                when (response) {
                    is Resource.Success -> {
                        hideLoading()
                        _airingTodayTvShowsState.value = response.data!!
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

    private fun fetchTrendingTvShows() {
        viewModelScope.launch {
            getTvShowsUseCase.getTrendingTvShows().collect { response ->
                when (response) {
                    is Resource.Success -> {
                        hideLoading()
                        _trendingTvShowsState.value = response.data!!
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

    private fun fetchTopRatedTvShows() {
        viewModelScope.launch {
            getTvShowsUseCase.getTopRatedTvShows().collect { response ->
                when (response) {
                    is Resource.Success -> {
                        hideLoading()
                        _topRatedTvShowsState.value = response.data!!
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

    private fun fetchPopularTvShows() {
        viewModelScope.launch {
            getTvShowsUseCase.getPopularTvShows().collect { response ->
                when (response) {
                    is Resource.Success -> {
                        hideLoading()
                        _popularTvShowsState.value = response.data!!
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