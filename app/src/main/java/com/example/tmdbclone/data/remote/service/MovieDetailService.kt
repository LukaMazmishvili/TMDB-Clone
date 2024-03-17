package com.example.tmdbclone.data.remote.service

import com.example.tmdbclone.common.Endpoints.BEARER_TOKEN
import com.example.tmdbclone.common.Endpoints.MOVIE_CAST
import com.example.tmdbclone.common.Endpoints.MOVIE_DETAILS
import com.example.tmdbclone.common.Endpoints.MOVIE_RECOMMENDATION
import com.example.tmdbclone.common.Endpoints.MOVIE_SIMILAR
import com.example.tmdbclone.common.Endpoints.MOVIE_VIDEOS
import com.example.tmdbclone.data.remote.model.CelebritiesModelDto
import com.example.tmdbclone.data.remote.model.MovieDetailsModelDto
import com.example.tmdbclone.data.remote.model.MoviesDTO
import com.example.tmdbclone.data.remote.model.VideoModelDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface MovieDetailService {

    @GET(MOVIE_DETAILS)
    suspend fun fetchMovieDetails(
        @Path("movieId") movieId: Int,
        @Header("accept") format: String = "Application/Json",
        @Header("Authorization") bearer: String = BEARER_TOKEN
    ): Response<MovieDetailsModelDto>

    @GET(MOVIE_CAST)
    suspend fun fetchMovieCast(
        @Path("movieId") movieId: Int,
        @Header("accept") format: String = "Application/Json",
        @Header("Authorization") bearer: String = BEARER_TOKEN
    ): Response<CelebritiesModelDto>

    @GET(MOVIE_VIDEOS)
    suspend fun fetchMovieVideos(
        @Path("movieId") movieId: Int,
        @Header("accept") format: String = "Application/Json",
        @Header("Authorization") bearer: String = BEARER_TOKEN
    ): Response<VideoModelDto>

    @GET(MOVIE_RECOMMENDATION)
    suspend fun fetchMovieRecommendation(
        @Path("movieId") movieId: Int,
        @Header("accept") format: String = "Application/Json",
        @Header("Authorization") bearer: String = BEARER_TOKEN
    ): Response<MoviesDTO>

    @GET(MOVIE_SIMILAR)
    suspend fun fetchMovieSimilar(
        @Path("movieId") movieId: Int,
        @Header("accept") format: String = "Application/Json",
        @Header("Authorization") bearer: String = BEARER_TOKEN
    ): Response<MoviesDTO>
}