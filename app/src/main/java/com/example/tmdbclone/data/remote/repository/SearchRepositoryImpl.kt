package com.example.tmdbclone.data.remote.repository

import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.fetchFlow
import com.example.tmdbclone.data.remote.model.SearchSimilarModelDto
import com.example.tmdbclone.data.remote.service.SearchService
import com.example.tmdbclone.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val searchService: SearchService) :
    SearchRepository {

    override suspend fun fetchSimilarSearches(query: String): Flow<Resource<SearchSimilarModelDto>> =
        flow {
            try {

                emit(Resource.Loading(true))

                val response = searchService.fetchSimilarSearches(query)

                val body = response.code()
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        emit(Resource.Success(body))
                    }
                } else {
                    emit(Resource.Error("Something Went Wrong !"))
                }
            } catch (e: Exception) {
                emit(Resource.Error("Something Went Wrong !"))
            }
        }
}