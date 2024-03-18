package com.example.tmdbclone.data.remote.service

import com.example.tmdbclone.common.Endpoints.BEARER_TOKEN
import com.example.tmdbclone.common.Endpoints.MOVIE_GENRES
import com.example.tmdbclone.common.Endpoints.TV_SHOW_GENRES
import com.example.tmdbclone.data.remote.model.GenresModelDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface GenresService {

    @GET(MOVIE_GENRES)
    suspend fun fetchMovieGenres(
        @Header("accept") format: String = "Application/Json",
        @Header("Authorization") bearer: String = BEARER_TOKEN
    ): Response<GenresModelDto>

    @GET(TV_SHOW_GENRES)
    suspend fun fetchTvShowGenres(
        @Header("accept") format: String = "Application/Json",
        @Header("Authorization") bearer: String = BEARER_TOKEN
    ): Response<GenresModelDto>

}