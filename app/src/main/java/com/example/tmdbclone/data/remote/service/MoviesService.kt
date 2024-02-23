package com.example.tmdbclone.data.remote.service

import com.example.tmdbclone.common.Endpoints.POPULAR_ENDPOINT
import com.example.tmdbclone.data.remote.model.PopularMovieDTO
import retrofit2.Response
import retrofit2.http.GET

interface MoviesService {

    @GET(POPULAR_ENDPOINT)
    suspend fun fetchPopularMovies(): Response<PopularMovieDTO>

}