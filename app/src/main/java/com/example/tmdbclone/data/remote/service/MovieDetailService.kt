package com.example.tmdbclone.data.remote.service

import com.example.tmdbclone.common.Endpoints
import com.example.tmdbclone.common.Endpoints.BEARER_TOKEN
import com.example.tmdbclone.common.Endpoints.MOVIE_CAST
import com.example.tmdbclone.common.Endpoints.MOVIE_DETAILS
import com.example.tmdbclone.common.Endpoints.MOVIE_RECOMMENDATION
import com.example.tmdbclone.common.Endpoints.MOVIE_SIMILAR
import com.example.tmdbclone.common.Endpoints.MOVIE_VIDEOS
import com.example.tmdbclone.data.remote.model.CelebritiesModelDto
import com.example.tmdbclone.data.remote.model.GenresModelDto
import com.example.tmdbclone.data.remote.model.MovieDetailsModelDto
import com.example.tmdbclone.data.remote.model.PopularMovieDTO
import com.example.tmdbclone.data.remote.model.VideoModelDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface MovieDetailService {

    @GET(MOVIE_DETAILS)
    suspend fun fetchMovieDetails(
        @Header("accept") format: String = "Application/Json",
        @Header("Authorization") bearer: String = BEARER_TOKEN
    ): Response<MovieDetailsModelDto>

    @GET(MOVIE_CAST)
    suspend fun fetchMovieCast(
        @Header("accept") format: String = "Application/Json",
        @Header("Authorization") bearer: String = BEARER_TOKEN
    ): Response<CelebritiesModelDto>

    @GET(MOVIE_VIDEOS)
    suspend fun fetchMovieVideos(
        @Header("accept") format: String = "Application/Json",
        @Header("Authorization") bearer: String = BEARER_TOKEN
    ): Response<List<VideoModelDto>>

    @GET(MOVIE_RECOMMENDATION)
    suspend fun fetchMovieRecommendation(
        @Header("accept") format: String = "Application/Json",
        @Header("Authorization") bearer: String = BEARER_TOKEN
    ): Response<PopularMovieDTO>

    @GET(MOVIE_SIMILAR)
    suspend fun fetchMovieSimilar(
        @Header("accept") format: String = "Application/Json",
        @Header("Authorization") bearer: String = BEARER_TOKEN
    ): Response<PopularMovieDTO>
}