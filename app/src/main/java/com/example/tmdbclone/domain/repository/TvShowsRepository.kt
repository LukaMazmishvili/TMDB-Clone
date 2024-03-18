package com.example.tmdbclone.domain.repository

import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.MoviesDTO
import com.example.tmdbclone.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface TvShowsRepository {

    suspend fun fetchAiringTodayTvShows(): Flow<Resource<MovieModel>>
    suspend fun fetchTrendingTvShows(): Flow<Resource<MovieModel>>
    suspend fun fetchTopRatedTvShows(): Flow<Resource<MovieModel>>
    suspend fun fetchPopularTvShows(): Flow<Resource<MovieModel>>

}