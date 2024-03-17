package com.example.tmdbclone.data.remote.service

import com.example.tmdbclone.common.Endpoints.AIRING_TODAY
import com.example.tmdbclone.common.Endpoints.BEARER_TOKEN
import com.example.tmdbclone.common.Endpoints.POPULAR_TV_SHOWS
import com.example.tmdbclone.common.Endpoints.TOP_RATED_TV_SHOWS
import com.example.tmdbclone.common.Endpoints.TRENDING_TV_SHOWS
import com.example.tmdbclone.data.remote.model.MoviesDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface TvShowsService {

    @GET(AIRING_TODAY)
    suspend fun fetchAiringTodayTvShows(
        @Query("page") page: String,
        @Header("accept") format: String = "Application/Json",
        @Header("Authorization") bearer: String = BEARER_TOKEN
    ): Response<MoviesDTO>

    @GET(TRENDING_TV_SHOWS)
    suspend fun fetchTrendingTvShows(
        @Query("page") page: String,
        @Header("accept") format: String = "Application/Json",
        @Header("Authorization") bearer: String = BEARER_TOKEN
    ): Response<MoviesDTO> // todo rename ro make base model class

    @GET(TOP_RATED_TV_SHOWS)
    suspend fun fetchTopRatedTvShows(
        @Query("page") page: String,
        @Header("accept") format: String = "Application/Json",
        @Header("Authorization") bearer: String = BEARER_TOKEN
    ): Response<MoviesDTO> // todo rename ro make base model class

    @GET(POPULAR_TV_SHOWS)
    suspend fun fetchPopularTvShows(
        @Query("page") page: String,
        @Header("accept") format: String = "Application/Json",
        @Header("Authorization") bearer: String = BEARER_TOKEN
    ): Response<MoviesDTO> // todo rename ro make base model class

}