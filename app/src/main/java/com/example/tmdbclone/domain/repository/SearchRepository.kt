package com.example.tmdbclone.domain.repository

import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.PopularMovieDTO
import com.example.tmdbclone.data.remote.model.SearchSimilarModelDto
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    suspend fun fetchSimilarSearches(query: String): Flow<Resource<SearchSimilarModelDto>>

}