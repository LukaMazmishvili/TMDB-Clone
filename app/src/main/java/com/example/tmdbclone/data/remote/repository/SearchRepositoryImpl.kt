package com.example.tmdbclone.data.remote.repository

import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.fetchFlow
import com.example.tmdbclone.data.remote.mapper.toSearchModel
import com.example.tmdbclone.data.remote.mapper.toSearchPersonModel
import com.example.tmdbclone.data.remote.mapper.toSearchSimilarModel
import com.example.tmdbclone.data.remote.model.SearchModelDto
import com.example.tmdbclone.data.remote.model.SearchPersonModelDto
import com.example.tmdbclone.data.remote.model.SearchSimilarModelDto
import com.example.tmdbclone.data.remote.service.SearchService
import com.example.tmdbclone.domain.model.SearchModel
import com.example.tmdbclone.domain.model.SearchPersonModel
import com.example.tmdbclone.domain.model.SearchSimilarModel
import com.example.tmdbclone.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val searchService: SearchService) :
    SearchRepository {

    override suspend fun fetchSimilarSearches(query: String): Flow<Resource<SearchSimilarModel>> =
        fetchFlow<SearchSimilarModelDto, SearchSimilarModel>(
            block = { searchService.fetchSimilarSearches(query) },
            mapper = {
                it.toSearchSimilarModel(genres)
            }
        )

    override suspend fun fetchSearchedMovies(
        query: String,
        page: Int
    ): Flow<Resource<SearchModel>> =
        fetchFlow<SearchModelDto, SearchModel>(
            block = { searchService.fetchSearchedMovies(query, page) },
            mapper = {
                it.toSearchModel(genres)
            }
        )

    override suspend fun fetchSearchedTvShows(
        query: String,
        page: Int
    ): Flow<Resource<SearchModel>> =
        fetchFlow<SearchModelDto, SearchModel>(
            block = { searchService.fetchSearchedTvShows(query, page) },
            mapper = {
                it.toSearchModel(genres)
            }
        )

    override suspend fun fetchSearchedCelebrities(
        query: String,
        page: Int
    ): Flow<Resource<SearchPersonModel>> =
        fetchFlow<SearchPersonModelDto, SearchPersonModel>(
            block = { searchService.fetchSearchedPersons(query, page) },
            mapper = {
                it.toSearchPersonModel()
            }
        )

}