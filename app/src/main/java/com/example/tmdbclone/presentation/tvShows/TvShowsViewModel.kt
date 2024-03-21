package com.example.tmdbclone.presentation.tvShows

import androidx.lifecycle.viewModelScope
import com.example.tmdbclone.base.BaseViewModel
import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.MoviesDTO
import com.example.tmdbclone.domain.model.MovieModel
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
        MutableStateFlow<List<MovieModel.Movie>>(emptyList())
    val airingTodayTvShowsState = _airingTodayTvShowsState.asStateFlow()

    private val _trendingTvShowsState =
        MutableStateFlow<List<MovieModel.Movie>>(emptyList())
    val trendingTvShowsState = _trendingTvShowsState.asStateFlow()

    private val _topRatedTvShowsState =
        MutableStateFlow<List<MovieModel.Movie>>(emptyList())
    val topRatedTvShowsState = _topRatedTvShowsState.asStateFlow()

    private val _popularTvShowsState =
        MutableStateFlow<List<MovieModel.Movie>>(emptyList())
    val popularTvShowsState = _popularTvShowsState.asStateFlow()

    init {
        fetchAiringTodayTvShows()
        fetchTopRatedTvShows()
        fetchPopularTvShows()
    }

    private fun fetchAiringTodayTvShows() {
        viewModelScope.launch(Dispatchers.IO) {
            networkStatus.collect { networkStatus ->
                networkStatus.let {
//                    if (networkStatus) {
                    getTvShowsUseCase.getAiringTodayTvShows().collect { response ->
                        when (response) {
                            is Resource.Success -> {
                                hideLoading()
                                _airingTodayTvShowsState.value = response.data!!.results!!
                            }

                            is Resource.Error -> {
                                hideLoading()
                                setErrorMsg(response.errorMsg)
                            }

                            is Resource.Loading -> showLoading()

                        }
                    }
//                    }
                }
            }
        }
    }

    private fun fetchTrendingTvShows() {
        viewModelScope.launch(Dispatchers.IO) {
            networkStatus.collect { networkStatus ->
                networkStatus.let {
//                    if (networkStatus) {
                    getTvShowsUseCase.getTrendingTvShows().collect { response ->
                        when (response) {
                            is Resource.Success -> {
                                hideLoading()
                                _trendingTvShowsState.value = response.data!!.results!!
                            }

                            is Resource.Error -> {
                                hideLoading()
                                setErrorMsg(response.errorMsg)
                            }

                            is Resource.Loading -> showLoading()

                        }
                    }
//                    }
                }
            }
        }
    }

    private fun fetchTopRatedTvShows() {
        viewModelScope.launch(Dispatchers.IO) {
            networkStatus.collect { networkStatus ->
//                networkStatus?.let {
                getTvShowsUseCase.getTopRatedTvShows().collect { response ->
                    when (response) {
                        is Resource.Success -> {
                            hideLoading()
                            _topRatedTvShowsState.value = response.data!!.results!!
                        }

                        is Resource.Error -> {
                            hideLoading()
                            setErrorMsg(response.errorMsg)
                        }

                        is Resource.Loading -> showLoading()

                    }
//                    }
                }
            }
        }
    }

    private fun fetchPopularTvShows() {
        viewModelScope.launch(Dispatchers.IO) {
            networkStatus.collect { networkStatus ->
//                networkStatus?.let {
                getTvShowsUseCase.getPopularTvShows().collect { response ->
                    when (response) {
                        is Resource.Success -> {
                            hideLoading()
                            _popularTvShowsState.value = response.data!!.results!!
                        }

                        is Resource.Error -> {
                            hideLoading()
                            setErrorMsg(response.errorMsg)
                        }

                        is Resource.Loading -> showLoading()

                    }
//                    }
                }
            }
        }
    }
}