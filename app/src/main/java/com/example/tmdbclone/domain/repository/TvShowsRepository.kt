package com.example.tmdbclone.domain.repository

import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.MoviesDTO
import kotlinx.coroutines.flow.Flow

interface TvShowsRepository {

    suspend fun fetchAiringTodayTvShows(): Flow<Resource<List<MoviesDTO.MovieModelDto>>>
    suspend fun fetchTrendingTvShows(): Flow<Resource<List<MoviesDTO.MovieModelDto>>>
    suspend fun fetchTopRatedTvShows(): Flow<Resource<List<MoviesDTO.MovieModelDto>>>
    suspend fun fetchPopularTvShows(): Flow<Resource<List<MoviesDTO.MovieModelDto>>>

}