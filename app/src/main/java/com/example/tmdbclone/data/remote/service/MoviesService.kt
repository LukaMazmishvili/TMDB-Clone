package com.example.tmdbclone.data.remote.service

import com.example.tmdbclone.common.Endpoints.BEARER_TOKEN
import com.example.tmdbclone.common.Endpoints.NOW_PLAYING
import com.example.tmdbclone.common.Endpoints.POPULAR_ENDPOINT
import com.example.tmdbclone.common.Endpoints.TOP_RATED
import com.example.tmdbclone.common.Endpoints.TRENDING_ALL
import com.example.tmdbclone.common.Endpoints.UPCOMING
import com.example.tmdbclone.data.remote.model.PopularMovieDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MoviesService {

    @GET(POPULAR_ENDPOINT)
    suspend fun fetchPopularMovies(
        @Query("page") page: Int,
        @Header("accept") format: String = "Application/Json",
        @Header("Authorization") bearer: String = BEARER_TOKEN
    ): Response<PopularMovieDTO>

    @GET(NOW_PLAYING)
    suspend fun fetchNowPlaying(
        @Query("page") page: String = "1",
        @Header("accept") format: String = "Application/Json",
        @Header("Authorization") bearer: String = BEARER_TOKEN
    ): Response<PopularMovieDTO> // todo rename ro make base model class

    @GET(TRENDING_ALL)
    suspend fun fetchTrendingMovies(
        @Query("page") page: String = "1",
        @Header("accept") format: String = "Application/Json",
        @Header("Authorization") bearer: String = BEARER_TOKEN
    ): Response<PopularMovieDTO> // todo rename ro make base model class

    @GET(TOP_RATED)
    suspend fun fetchTopRatedMovies(
        @Query("page") page: String = "1",
        @Header("accept") format: String = "Application/Json",
        @Header("Authorization") bearer: String = BEARER_TOKEN
    ): Response<PopularMovieDTO> // todo rename ro make base model class

    @GET(UPCOMING)
    suspend fun fetchUpcomingMovies(
        @Query("page") page: String = "1",
        @Header("accept") format: String = "Application/Json",
        @Header("Authorization") bearer: String = BEARER_TOKEN
    ): Response<PopularMovieDTO> // todo rename ro make base model class

}