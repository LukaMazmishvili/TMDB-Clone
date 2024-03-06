package com.example.tmdbclone.domain.usecase

import com.example.tmdbclone.domain.repository.MovieDetailRepository
import com.example.tmdbclone.domain.repository.UserRepository
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val movieDetailRepository: MovieDetailRepository,
    private val userRepository: UserRepository
) {

    suspend fun getMovieDetails(movieId: Int) = movieDetailRepository.fetchMovieDetails(movieId)

    suspend fun getMovieCast(movieId: Int) = movieDetailRepository.fetchMovieCast(movieId)

    suspend fun getMovieVideos(movieId: Int) = movieDetailRepository.fetchMovieVideos(movieId)

    suspend fun getMovieRecommended(movieId: Int) =
        movieDetailRepository.fetchMovieRecommend(movieId)

    suspend fun getMovieSimilar(movieId: Int) = movieDetailRepository.fetchMovieSimilar(movieId)
}