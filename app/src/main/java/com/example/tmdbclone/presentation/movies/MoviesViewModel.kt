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

    init {
        fetchMovies()
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

}