package com.example.tmdbclone.data.remote.service

import com.example.tmdbclone.common.Endpoints.BEARER_TOKEN
import com.example.tmdbclone.common.Endpoints.POPULAR_ENDPOINT
import com.example.tmdbclone.data.remote.model.PopularMovieDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface MoviesService {

    @GET(POPULAR_ENDPOINT)
    suspend fun fetchPopularMovies(
        @Header("accept") format: String = "Application/Json",
        @Header("Authorization") bearer: String = BEARER_TOKEN
    ): Response<PopularMovieDTO>

}