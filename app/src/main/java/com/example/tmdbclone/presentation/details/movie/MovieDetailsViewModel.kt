package com.example.tmdbclone.presentation.details.movie

import androidx.lifecycle.viewModelScope
import com.example.tmdbclone.base.BaseViewModel
import com.example.tmdbclone.common.MediaTypes
import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.CelebritiesModelDto
import com.example.tmdbclone.data.remote.model.MovieDetailsModelDto
import com.example.tmdbclone.data.remote.model.MoviesDTO
import com.example.tmdbclone.data.remote.model.VideoModelDto
import com.example.tmdbclone.domain.model.CelebritiesModel
import com.example.tmdbclone.domain.model.MovieDetailsModel
import com.example.tmdbclone.domain.model.MovieModel
import com.example.tmdbclone.domain.usecase.GetMovieDetailsUseCase
import com.example.tmdbclone.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val userUseCase: UserUseCase
) : BaseViewModel() {

    val movieIdState = MutableStateFlow<Int>(-1)
    val mediaTypeState = MutableStateFlow<MediaTypes>(MediaTypes.None)

    val isAuthorized = userUseCase.isAuthorized()

    private val _moviesDetailsState =
        MutableStateFlow<MovieDetailsModel?>(null)
    val moviesDetailsState = _moviesDetailsState.asStateFlow()

    private val _moviesCastState =
        MutableStateFlow<CelebritiesModel?>(null)
    val moviesCastState = _moviesCastState.asStateFlow()

    private val _moviesVideosState =
        MutableStateFlow<VideoModelDto?>(null)
    val moviesVideosState = _moviesVideosState.asStateFlow()

    private val _moviesRecommendedState =
        MutableStateFlow<MovieModel?>(null)
    val moviesRecommendedState = _moviesRecommendedState.asStateFlow()

    private val _moviesSimilarState =
        MutableStateFlow<MovieModel?>(null)
    val moviesSimilarState = _moviesSimilarState.asStateFlow()

    private val _isFavouriteState =
        MutableSharedFlow<Boolean>()
    val isFavouriteState = _isFavouriteState.asSharedFlow()

    private val _removeFavouriteState =
        MutableSharedFlow<Boolean>()
    val removeFavouriteState = _removeFavouriteState.asSharedFlow()

    init {
        fetchMovieDetails()
        fetchMovieCast()
        fetchMovieVideos()
        fetchMovieRecommended()
        fetchMovieSimilar()
    }

    fun addToFavourites(movieId: Int) {
        viewModelScope.launch {
            userUseCase.addFavourite(movieId).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        hideLoading()
                        _isFavouriteState.emit(true)
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

    fun removeFavourite(movieId: Int) {
        viewModelScope.launch {
            userUseCase.removeFavourite(movieId).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        hideLoading()
                        _removeFavouriteState.emit(true)
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

    fun isFavourite(movieId: Int) {
        viewModelScope.launch {
            userUseCase.isFavourite(movieId).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        hideLoading()
                        _isFavouriteState.emit(response.data!!.isAdded)
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

    private fun fetchMovieDetails() {
        viewModelScope.launch {

            movieIdState.combine(mediaTypeState) { movieId, mediaType ->
                Pair(movieId, mediaType)
            }.collect { (movieId, mediaType) ->
                getMovieDetailsUseCase.getMovieDetails(movieId, mediaType).collect { response ->
                    when (response) {
                        is Resource.Success -> {
                            hideLoading()
                            _moviesDetailsState.value = response.data!!
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

    private fun fetchMovieCast() {
        viewModelScope.launch {
            movieIdState.collect { movieId ->
                getMovieDetailsUseCase.getMovieCast(movieId).collect { response ->
                    when (response) {
                        is Resource.Success -> {
                            hideLoading()
                            _moviesCastState.value = response.data!!
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

    private fun fetchMovieVideos() {
        viewModelScope.launch {
            movieIdState.collect { movieId ->
                getMovieDetailsUseCase.getMovieVideos(movieId).collect { response ->
                    when (response) {
                        is Resource.Success -> {
                            hideLoading()
                            _moviesVideosState.value = response.data!!
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

    private fun fetchMovieRecommended() {
        viewModelScope.launch {
            movieIdState.collect { movieId ->
                getMovieDetailsUseCase.getMovieRecommended(movieId).collect { response ->
                    when (response) {
                        is Resource.Success -> {
                            hideLoading()
                            _moviesRecommendedState.value = response.data!!
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

    private fun fetchMovieSimilar() {
        viewModelScope.launch {
            movieIdState.collect { movieId ->
                getMovieDetailsUseCase.getMovieSimilar(movieId).collect { response ->
                    when (response) {
                        is Resource.Success -> {
                            hideLoading()
                            _moviesSimilarState.value = response.data!!
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