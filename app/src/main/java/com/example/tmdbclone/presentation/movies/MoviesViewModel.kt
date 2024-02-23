package com.example.tmdbclone.presentation.movies

import com.example.tmdbclone.base.BaseViewModel
import com.example.tmdbclone.data.remote.model.PopularMovieDTO
import com.example.tmdbclone.domain.usecase.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val getMoviesUseCase: GetMoviesUseCase): BaseViewModel() {

    private val _moviesState = MutableStateFlow<List<PopularMovieDTO.MovieModelDto>>(emptyList())

}