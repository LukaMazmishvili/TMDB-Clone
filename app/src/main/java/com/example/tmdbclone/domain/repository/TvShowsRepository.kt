package com.example.tmdbclone.domain.repository

import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.PopularMovieDTO
import kotlinx.coroutines.flow.Flow

interface TvShowsRepository {

    suspend fun fetchAiringTodayTvShows(): Flow<Resource<List<PopularMovieDTO.MovieModelDto>>>
    suspend fun fetchTrendingTvShows(): Flow<Resource<List<PopularMovieDTO.MovieModelDto>>>
    suspend fun fetchTopRatedTvShows(): Flow<Resource<List<PopularMovieDTO.MovieModelDto>>>
    suspend fun fetchPopularTvShows(): Flow<Resource<List<PopularMovieDTO.MovieModelDto>>>

}