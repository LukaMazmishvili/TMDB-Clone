package com.example.tmdbclone.domain.repository

import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.MoviesDTO
import com.example.tmdbclone.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun fetchPopularMovies(page: Int): Flow<Resource<MovieModel>>

    suspend fun fetchNowPlaying(): Flow<Resource<MovieModel>>

    suspend fun fetchTrendingMovies(): Flow<Resource<MovieModel>>

    suspend fun fetchTopRatedMovies(): Flow<Resource<MovieModel>>

    suspend fun fetchUpcomingMovies(): Flow<Resource<MovieModel>>
    suspend fun genres()
}