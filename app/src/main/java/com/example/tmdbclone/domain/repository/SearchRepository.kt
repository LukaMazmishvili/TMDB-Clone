package com.example.tmdbclone.domain.repository

import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.SearchModelDto
import com.example.tmdbclone.data.remote.model.SearchPersonModelDto
import com.example.tmdbclone.data.remote.model.SearchSimilarModelDto
import com.example.tmdbclone.domain.model.SearchModel
import com.example.tmdbclone.domain.model.SearchPersonModel
import com.example.tmdbclone.domain.model.SearchSimilarModel
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    suspend fun fetchSimilarSearches(query: String): Flow<Resource<SearchSimilarModel>>

    suspend fun fetchSearchedMovies(query: String, page: Int): Flow<Resource<SearchModel>>

    suspend fun fetchSearchedTvShows(query: String, page: Int): Flow<Resource<SearchModel>>

    suspend fun fetchSearchedCelebrities(
        query: String,
        page: Int
    ): Flow<Resource<SearchPersonModel>>

}