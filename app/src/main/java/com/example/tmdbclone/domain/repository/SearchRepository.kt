package com.example.tmdbclone.domain.repository

import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.PopularMovieDTO
import com.example.tmdbclone.data.remote.model.SearchModelDto
import com.example.tmdbclone.data.remote.model.SearchPersonModelDto
import com.example.tmdbclone.data.remote.model.SearchSimilarModelDto
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    suspend fun fetchSimilarSearches(query: String): Flow<Resource<SearchSimilarModelDto>>

    suspend fun fetchSearchedMovies(query: String, page: Int): Flow<Resource<SearchModelDto>>

    suspend fun fetchSearchedTvShows(query: String, page: Int): Flow<Resource<SearchModelDto>>

    suspend fun fetchSearchedCelebrities(
        query: String,
        page: Int
    ): Flow<Resource<SearchPersonModelDto>>

}