package com.example.tmdbclone.data.remote.service

import com.example.tmdbclone.common.Endpoints
import com.example.tmdbclone.common.Endpoints.TV_SHOW_DETAILS
import com.example.tmdbclone.data.remote.model.CelebritiesModelDto
import com.example.tmdbclone.data.remote.model.MovieDetailsModelDto
import com.example.tmdbclone.data.remote.model.MoviesDTO
import com.example.tmdbclone.data.remote.model.VideoModelDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface TvShowDetailsService {

    @GET(TV_SHOW_DETAILS)
    suspend fun fetchTvShowDetails(
        @Path("movieId") movieId: Int,
        @Header("accept") format: String = "Application/Json",
        @Header("Authorization") bearer: String = Endpoints.BEARER_TOKEN
    ): Response<MovieDetailsModelDto>

}