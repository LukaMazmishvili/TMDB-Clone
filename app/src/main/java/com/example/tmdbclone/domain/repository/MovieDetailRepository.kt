package com.example.tmdbclone.domain.repository

import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.CelebritiesModelDto
import com.example.tmdbclone.data.remote.model.MovieDetailsModelDto
import com.example.tmdbclone.data.remote.model.PopularMovieDTO
import com.example.tmdbclone.data.remote.model.VideoModelDto
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepository {

    suspend fun fetchMovieDetails(): Flow<Resource<MovieDetailsModelDto>>

    suspend fun fetchMovieCast(): Flow<Resource<CelebritiesModelDto>>

    suspend fun fetchMovieVideos(): Flow<Resource<List<VideoModelDto>>>

    suspend fun fetchMovieRecommend(): Flow<Resource<PopularMovieDTO>>

    suspend fun fetchMovieSimilar(): Flow<Resource<PopularMovieDTO>>
}