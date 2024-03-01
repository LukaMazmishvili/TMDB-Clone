package com.example.tmdbclone.domain.repository

import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.CelebritiesModelDto
import com.example.tmdbclone.data.remote.model.MovieDetailsModelDto
import com.example.tmdbclone.data.remote.model.PopularMovieDTO
import com.example.tmdbclone.data.remote.model.VideoModelDto
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepository {

    suspend fun fetchMovieDetails(movieId: Int): Flow<Resource<MovieDetailsModelDto>>

    suspend fun fetchMovieCast(movieId: Int): Flow<Resource<CelebritiesModelDto>>

    suspend fun fetchMovieVideos(movieId: Int): Flow<Resource<VideoModelDto>>

    suspend fun fetchMovieRecommend(movieId: Int): Flow<Resource<PopularMovieDTO>>

    suspend fun fetchMovieSimilar(movieId: Int): Flow<Resource<PopularMovieDTO>>
}