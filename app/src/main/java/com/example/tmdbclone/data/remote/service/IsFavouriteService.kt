package com.example.tmdbclone.data.remote.service

import com.example.tmdbclone.common.Endpoints
import com.example.tmdbclone.common.Endpoints.IS_FAVOURITE
import com.example.tmdbclone.data.remote.model.FavouriteResponseDto
import com.example.tmdbclone.data.remote.model.MovieDetailsModelDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface IsFavouriteService {

    @GET(IS_FAVOURITE)
    suspend fun checkFavourite(
        @Path("movieId") movieId: Int,
        @Header("accept") format: String = "Application/Json",
        @Header("Authorization") bearer: String
    ): Response<FavouriteResponseDto>
}