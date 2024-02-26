package com.example.tmdbclone.domain.usecase

import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.PopularMovieDTO
import com.example.tmdbclone.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke() = moviesRepository.fetchMovies()

    suspend fun getNowPlayingMovies() = moviesRepository.fetchNowPlaying()

    suspend fun getTrendingMovies() = moviesRepository.fetchTrendingMovies()

    suspend fun getTopRatedMovies() = moviesRepository.fetchTopRatedMovies()

}