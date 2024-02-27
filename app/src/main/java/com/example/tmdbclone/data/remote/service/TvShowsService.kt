package com.example.tmdbclone.data.remote.service

import com.example.tmdbclone.common.Endpoints.AIRING_TODAY
import com.example.tmdbclone.common.Endpoints.BEARER_TOKEN
import com.example.tmdbclone.common.Endpoints.NOW_PLAYING
import com.example.tmdbclone.common.Endpoints.POPULAR_ENDPOINT
import com.example.tmdbclone.common.Endpoints.POPULAR_TV_SHOWS
import com.example.tmdbclone.common.Endpoints.TOP_RATED
import com.example.tmdbclone.common.Endpoints.TOP_RATED_TV_SHOWS
import com.example.tmdbclone.common.Endpoints.TRENDING_ALL
import com.example.tmdbclone.common.Endpoints.TRENDING_TV_SHOWS
import com.example.tmdbclone.common.Endpoints.UPCOMING
import com.example.tmdbclone.data.remote.model.PopularMovieDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface TvShowsService {

    @GET(AIRING_TODAY)
    suspend fun fetchAiringTodayTvShows(
        @Header("accept") format: String = "Application/Json",
        @Header("Authorization") bearer: String = BEARER_TOKEN
    ): Response<PopularMovieDTO>

    @GET(TRENDING_TV_SHOWS)
    suspend fun fetchTrendingTvShows(
        @Header("accept") format: String = "Application/Json",
        @Header("Authorization") bearer: String = BEARER_TOKEN
    ): Response<PopularMovieDTO> // todo rename ro make base model class

    @GET(TOP_RATED_TV_SHOWS)
    suspend fun fetchTopRatedTvShows(
        @Header("accept") format: String = "Application/Json",
        @Header("Authorization") bearer: String = BEARER_TOKEN
    ): Response<PopularMovieDTO> // todo rename ro make base model class

    @GET(POPULAR_TV_SHOWS)
    suspend fun fetchPopularTvShows(
        @Header("accept") format: String = "Application/Json",
        @Header("Authorization") bearer: String = BEARER_TOKEN
    ): Response<PopularMovieDTO> // todo rename ro make base model class

}