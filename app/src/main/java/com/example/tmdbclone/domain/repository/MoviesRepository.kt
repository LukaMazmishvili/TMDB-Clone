package com.example.tmdbclone.domain.repository

import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.PopularMovieDTO
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun fetchMovies(): Flow<Resource<List<PopularMovieDTO.MovieModelDto>>>

    suspend fun fetchNowPlaying(): Flow<Resource<List<PopularMovieDTO.MovieModelDto>>>

    suspend fun fetchTrendingMovies(): Flow<Resource<List<PopularMovieDTO.MovieModelDto>>>

    suspend fun fetchTopRatedMovies(): Flow<Resource<List<PopularMovieDTO.MovieModelDto>>>
}