package com.example.tmdbclone.presentation.tvShows

import androidx.lifecycle.viewModelScope
import com.example.tmdbclone.base.BaseViewModel
import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.MoviesDTO
import com.example.tmdbclone.domain.usecase.GetTvShowsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowsViewModel @Inject constructor(private val getTvShowsUseCase: GetTvShowsUseCase) :
    BaseViewModel() {

    private val _airingTodayTvShowsState =
        MutableStateFlow<List<MoviesDTO.MovieModelDto>>(emptyList())
    val airingTodayTvShowsState = _airingTodayTvShowsState.asStateFlow()

    private val _trendingTvShowsState =
        MutableStateFlow<List<MoviesDTO.MovieModelDto>>(emptyList())
    val trendingTvShowsState = _trendingTvShowsState.asStateFlow()

    private val _topRatedTvShowsState =
        MutableStateFlow<List<MoviesDTO.MovieModelDto>>(emptyList())
    val topRatedTvShowsState = _topRatedTvShowsState.asStateFlow()

    private val _popularTvShowsState =
        MutableStateFlow<List<MoviesDTO.MovieModelDto>>(emptyList())
    val popularTvShowsState = _popularTvShowsState.asStateFlow()

    init {
        fetchAiringTodayTvShows()
        fetchTrendingTvShows()
        fetchTopRatedTvShows()
        fetchPopularTvShows()
    }

    fun fetchAiringTodayTvShows() {
        viewModelScope.launch(Dispatchers.IO) {
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

    fun fetchTrendingTvShows() {
        viewModelScope.launch(Dispatchers.IO) {
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

    fun fetchTopRatedTvShows() {
        viewModelScope.launch(Dispatchers.IO) {
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

    fun fetchPopularTvShows() {
        viewModelScope.launch(Dispatchers.IO) {
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