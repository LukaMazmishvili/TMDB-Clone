package com.example.tmdbclone.data.remote.service

import com.example.tmdbclone.common.Endpoints.BEARER_TOKEN
import com.example.tmdbclone.common.Endpoints.POPULAR_CELEBRITIES
import com.example.tmdbclone.common.Endpoints.TRENDING_CELEBRITIES
import com.example.tmdbclone.data.remote.model.CelebritiesModelDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface CelebritiesService {

    @GET(POPULAR_CELEBRITIES)
    suspend fun fetchPopularCelebrities(
        @Header("accept") format: String = "Application/Json",
        @Header("Authorization") bearer: String = BEARER_TOKEN
    ): Response<CelebritiesModelDto>

    @GET(TRENDING_CELEBRITIES)
    suspend fun fetchTrendingCelebrities(
        @Header("accept") format: String = "Application/Json",
        @Header("Authorization") bearer: String = BEARER_TOKEN
    ): Response<CelebritiesModelDto>
}