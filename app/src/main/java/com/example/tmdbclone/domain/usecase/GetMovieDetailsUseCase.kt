package com.example.tmdbclone.domain.usecase

import com.example.tmdbclone.domain.repository.MovieDetailRepository
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val movieDetailRepository: MovieDetailRepository) {

    suspend fun getMovieDetails(movieId: Int) = movieDetailRepository.fetchMovieDetails(movieId)

    suspend fun getMovieCast(movieId: Int) = movieDetailRepository.fetchMovieCast(movieId)

    suspend fun getMovieVideos(movieId: Int) = movieDetailRepository.fetchMovieVideos(movieId)

    suspend fun getMovieRecommended(movieId: Int) = movieDetailRepository.fetchMovieRecommend(movieId)

    suspend fun getMovieSimilar(movieId: Int) = movieDetailRepository.fetchMovieSimilar(movieId)

}