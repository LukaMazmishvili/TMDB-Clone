package com.example.tmdbclone.data.remote.service

import com.example.tmdbclone.common.Endpoints
import com.example.tmdbclone.common.Endpoints.CELEBRITY_DETAILS
import com.example.tmdbclone.common.Endpoints.CELEBRITY_MOVIE_CREDITS
import com.example.tmdbclone.common.Endpoints.CELEBRITY_TV_SHOWS_CREDITS
import com.example.tmdbclone.data.remote.model.CelebrityDetailsModelDto
import com.example.tmdbclone.data.remote.model.MoviesDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface CelebrityDetailsService {

    @GET(CELEBRITY_DETAILS)
    suspend fun fetchCelebrityDetails(
        @Path("personID") personId: Int,
        @Header("accept") format: String = "Application/Json",
        @Header("Authorization") bearer: String = Endpoints.BEARER_TOKEN
    ): Response<CelebrityDetailsModelDto>

    @GET(CELEBRITY_MOVIE_CREDITS)
    suspend fun fetchCelebrityMovieCredits(
        @Path("personID") personId: Int,
        @Header("accept") format: String = "Application/Json",
        @Header("Authorization") bearer: String = Endpoints.BEARER_TOKEN
    ): Response<List<MoviesDTO>>

    @GET(CELEBRITY_TV_SHOWS_CREDITS)
    suspend fun fetchCelebrityTvShowsCredits(
        @Path("personID") personId: Int,
        @Header("accept") format: String = "Application/Json",
        @Header("Authorization") bearer: String = Endpoints.BEARER_TOKEN
    ): Response<List<MoviesDTO>>

}