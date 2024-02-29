package com.example.tmdbclone.domain.usecase

import com.example.tmdbclone.domain.repository.MovieDetailRepository
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val movieDetailRepository: MovieDetailRepository) {

    suspend fun getMovieDetails() = movieDetailRepository.fetchMovieDetails()

    suspend fun getMovieCast() = movieDetailRepository.fetchMovieCast()

    suspend fun getMovieVideos() = movieDetailRepository.fetchMovieVideos()

    suspend fun getMovieRecommended() = movieDetailRepository.fetchMovieRecommend()

    suspend fun getMovieSimilar() = movieDetailRepository.fetchMovieSimilar()

}