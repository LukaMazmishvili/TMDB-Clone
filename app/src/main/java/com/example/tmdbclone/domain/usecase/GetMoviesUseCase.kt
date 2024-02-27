package com.example.tmdbclone.domain.usecase

import com.example.tmdbclone.domain.repository.MoviesRepository
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    suspend fun getPopularMovies() = moviesRepository.fetchPopularMovies()

    suspend fun getNowPlayingMovies() = moviesRepository.fetchNowPlaying()

    suspend fun getTrendingMovies() = moviesRepository.fetchTrendingMovies()

    suspend fun getTopRatedMovies() = moviesRepository.fetchTopRatedMovies()

    suspend fun getUpcomingMovies() = moviesRepository.fetchUpcomingMovies()

}