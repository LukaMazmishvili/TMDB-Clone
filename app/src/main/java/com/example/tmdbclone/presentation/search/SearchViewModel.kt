package com.example.tmdbclone.presentation.search

import androidx.lifecycle.viewModelScope
import com.example.tmdbclone.base.BaseViewModel
import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.CelebritiesModelDto
import com.example.tmdbclone.data.remote.model.MoviesDTO
import com.example.tmdbclone.data.remote.model.SearchSimilarModelDto
import com.example.tmdbclone.domain.model.CelebritiesModel
import com.example.tmdbclone.domain.model.MovieModel
import com.example.tmdbclone.domain.model.SearchSimilarModel
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

    private var query: String = ""

    private val _similarSearchesState =
        MutableSharedFlow<List<SearchSimilarModel.SimilarSearches>>()
    val similarSearchesState = _similarSearchesState.asSharedFlow()

    private val _searchedMoviesState =
        MutableStateFlow<List<MovieModel.Movie>>(emptyList())
    val searchedMoviesState = _searchedMoviesState.asStateFlow()

    private val _searchedTvShowsState =
        MutableStateFlow<List<MovieModel.Movie>>(emptyList())
    val searchedTvShowsState = _searchedTvShowsState.asStateFlow()

    private val _searchedCelebritiesState =
        MutableStateFlow<List<CelebritiesModel.Result>>(emptyList())
    val searchedCelebritiesState = _searchedCelebritiesState.asStateFlow()

    fun setQuery(query: String) {
        this.query = query
    }

    fun getQuery(): String {
        return query
    }

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

    fun getSearchedMovies(query: String, page: Int = 1) {
        viewModelScope.launch {
            getSearchUseCase.getSearchedMovies(query, page).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        hideLoading()
                        _searchedMoviesState.value = response.data!!.results
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

    fun getSearchedTvShows(query: String, page: Int = 1) {
        viewModelScope.launch {
            getSearchUseCase.getSearchedTvShows(query, page).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        hideLoading()
                        _searchedTvShowsState.value = response.data!!.results
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

    fun getSearchedCelebrities(query: String, page: Int = 1) {
        viewModelScope.launch {
            getSearchUseCase.getSearchedCelebrities(query, page).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        hideLoading()
                        _searchedCelebritiesState.value = response.data!!.results
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