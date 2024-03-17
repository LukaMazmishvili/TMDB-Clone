package com.example.tmdbclone.domain.repository

import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.MoviesDTO
import com.example.tmdbclone.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun fetchPopularMovies(page: Int): Flow<Resource<List<MoviesDTO.MovieModelDto>>>

    suspend fun fetchNowPlaying(): Flow<Resource<List<MoviesDTO.MovieModelDto>>>

    suspend fun fetchTrendingMovies(): Flow<Resource<List<MoviesDTO.MovieModelDto>>>

    suspend fun fetchTopRatedMovies(): Flow<Resource<List<MoviesDTO.MovieModelDto>>>

    suspend fun fetchUpcomingMovies(): Flow<Resource<List<MoviesDTO.MovieModelDto>>>
}