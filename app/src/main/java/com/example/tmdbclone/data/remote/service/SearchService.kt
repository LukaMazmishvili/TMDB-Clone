package com.example.tmdbclone.data.remote.service

import com.example.tmdbclone.common.Endpoints.BEARER_TOKEN
import com.example.tmdbclone.common.Endpoints.SEARCH_MOVIES
import com.example.tmdbclone.common.Endpoints.SEARCH_MULTI
import com.example.tmdbclone.common.Endpoints.SEARCH_PERSON
import com.example.tmdbclone.common.Endpoints.SEARCH_TV_SHOWS
import com.example.tmdbclone.data.remote.model.SearchModelDto
import com.example.tmdbclone.data.remote.model.SearchPersonModelDto
import com.example.tmdbclone.data.remote.model.SearchSimilarModelDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SearchService {

    @GET(SEARCH_MOVIES)
    suspend fun fetchSearchedMovies(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Header("accept") format: String = "Application/Json",
        @Header("Authorization") bearer: String = BEARER_TOKEN
    ): Response<SearchModelDto>

    @GET(SEARCH_TV_SHOWS)
    suspend fun fetchSearchedTvShows(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Header("accept") format: String = "Application/Json",
        @Header("Authorization") bearer: String = BEARER_TOKEN
    ): Response<SearchModelDto>

    @GET(SEARCH_PERSON)
    suspend fun fetchSearchedPersons(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Header("accept") format: String = "Application/Json",
        @Header("Authorization") bearer: String = BEARER_TOKEN
    ): Response<SearchPersonModelDto>

    @GET(SEARCH_MULTI)
    suspend fun fetchSimilarSearches(
        @Query("query") query: String,
        @Header("accept") format: String = "Application/Json",
        @Header("Authorization") bearer: String = BEARER_TOKEN
    ): Response<SearchSimilarModelDto>
}