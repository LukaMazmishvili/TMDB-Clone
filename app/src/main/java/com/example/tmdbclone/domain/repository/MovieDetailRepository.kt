package com.example.tmdbclone.domain.repository

import com.example.tmdbclone.common.MediaTypes
import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.CelebritiesModelDto
import com.example.tmdbclone.data.remote.model.FavouriteResponseDto
import com.example.tmdbclone.data.remote.model.MovieDetailsModelDto
import com.example.tmdbclone.data.remote.model.MoviesDTO
import com.example.tmdbclone.data.remote.model.VideoModelDto
import com.example.tmdbclone.domain.model.CelebritiesModel
import com.example.tmdbclone.domain.model.MovieDetailsModel
import com.example.tmdbclone.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepository {

    suspend fun checkIfFavourite(movieId: Int, token: String): Flow<Resource<FavouriteResponseDto>>

    suspend fun fetchMovieGenres(): Map<Int, String>

    suspend fun fetchMovieDetails(
        movieId: Int,
        mediaType: MediaTypes
    ): Flow<Resource<MovieDetailsModel>>

    suspend fun fetchMovieCast(movieId: Int): Flow<Resource<CelebritiesModel>>

    suspend fun fetchMovieVideos(movieId: Int): Flow<Resource<VideoModelDto>>

    suspend fun fetchMovieRecommend(movieId: Int): Flow<Resource<MovieModel>>

    suspend fun fetchMovieSimilar(movieId: Int): Flow<Resource<MovieModel>>
}