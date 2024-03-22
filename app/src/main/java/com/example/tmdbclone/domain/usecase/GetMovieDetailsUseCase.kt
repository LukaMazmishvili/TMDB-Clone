package com.example.tmdbclone.domain.usecase

import com.example.tmdbclone.common.MediaTypes
import com.example.tmdbclone.domain.repository.MovieDetailRepository
import com.example.tmdbclone.domain.repository.UserRepository
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val movieDetailRepository: MovieDetailRepository,
    private val userRepository: UserRepository
) {

    suspend fun isFavourite(movieId: Int, token: String) =
        movieDetailRepository.checkIfFavourite(movieId, token)

    suspend fun getGenres(movieId: Int) = movieDetailRepository.fetchMovieGenres()

    suspend fun getMovieDetails(movieId: Int, mediaType: MediaTypes) =
        movieDetailRepository.fetchMovieDetails(movieId, mediaType)

    suspend fun getMovieCast(movieId: Int) = movieDetailRepository.fetchMovieCast(movieId)

    suspend fun getMovieVideos(movieId: Int) = movieDetailRepository.fetchMovieVideos(movieId)

    suspend fun getMovieRecommended(movieId: Int) =
        movieDetailRepository.fetchMovieRecommend(movieId)

    suspend fun getMovieSimilar(movieId: Int) = movieDetailRepository.fetchMovieSimilar(movieId)
}