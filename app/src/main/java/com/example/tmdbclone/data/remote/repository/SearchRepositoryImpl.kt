package com.example.tmdbclone.data.remote.repository

import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.fetchFlow
import com.example.tmdbclone.data.remote.model.SearchModelDto
import com.example.tmdbclone.data.remote.model.SearchPersonModelDto
import com.example.tmdbclone.data.remote.model.SearchSimilarModelDto
import com.example.tmdbclone.data.remote.service.SearchService
import com.example.tmdbclone.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val searchService: SearchService) :
    SearchRepository {

    override suspend fun fetchSimilarSearches(query: String): Flow<Resource<SearchSimilarModelDto>> =
        fetchFlow {
            searchService.fetchSimilarSearches(query)
        }

    override suspend fun fetchSearchedMovies(query: String): Flow<Resource<SearchModelDto>> =
        fetchFlow {
            searchService.fetchSearchedMovies(query)
        }

    override suspend fun fetchSearchedTvShows(query: String): Flow<Resource<SearchModelDto>> =
        fetchFlow {
            searchService.fetchSearchedTvShows(query)
        }

    override suspend fun fetchSearchedCelebrities(query: String): Flow<Resource<SearchPersonModelDto>> =
        fetchFlow {
            searchService.fetchSearchedPersons(query)
        }

}