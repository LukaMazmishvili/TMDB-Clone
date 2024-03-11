package com.example.tmdbclone.presentation.search

import androidx.lifecycle.viewModelScope
import com.example.tmdbclone.base.BaseViewModel
import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.CelebritiesModelDto
import com.example.tmdbclone.data.remote.model.PopularMovieDTO
import com.example.tmdbclone.data.remote.model.SearchModelDto
import com.example.tmdbclone.data.remote.model.SearchPersonModelDto
import com.example.tmdbclone.data.remote.model.SearchSimilarModelDto
import com.example.tmdbclone.domain.usecase.GetSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val getSearchUseCase: GetSearchUseCase) :
    BaseViewModel() {

    private val _similarSearchesState =
        MutableSharedFlow<List<SearchSimilarModelDto.SimilarSearches>>()
    val similarSearchesState = _similarSearchesState.asSharedFlow()

    private val _searchedMoviesState =
        MutableStateFlow<List<PopularMovieDTO>>(emptyList())
    val searchedMoviesState = _searchedMoviesState.asStateFlow()

    private val _searchedTvShowsState =
        MutableStateFlow<List<PopularMovieDTO>>(emptyList())
    val searchedTvShowsState = _searchedTvShowsState.asStateFlow()

    private val _searchedCelebritiesState =
        MutableStateFlow<List<CelebritiesModelDto>>(emptyList())
    val searchedCelebritiesState = _searchedCelebritiesState.asStateFlow()

    fun getSimilarSearches(query: String) {
        viewModelScope.launch {
            getSearchUseCase.getSimilarSearches(query).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        hideLoading()
                        _similarSearchesState.emit(response.data!!.results)
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

    fun getSearchedData(query: String) {
        viewModelScope.launch {
            getSearchUseCase.getSearchedMovies(query).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        hideLoading()
                        _searchedMoviesState.emit(response.data!!.results)
                    }

                    is Resource.Error -> {
                        hideLoading()
                        setErrorMsg(response.errorMsg)
                    }

                    is Resource.Loading -> showLoading()

                }
            }
        }

        viewModelScope.launch {
            getSearchUseCase.getSearchedTvShows(query).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        hideLoading()
                        _searchedTvShowsState.emit(response.data!!.results)
                    }

                    is Resource.Error -> {
                        hideLoading()
                        setErrorMsg(response.errorMsg)
                    }

                    is Resource.Loading -> showLoading()

                }
            }
        }

        viewModelScope.launch {
            getSearchUseCase.getSearchedCelebrities(query).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        hideLoading()
                        _searchedCelebritiesState.emit(response.data!!.result)
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