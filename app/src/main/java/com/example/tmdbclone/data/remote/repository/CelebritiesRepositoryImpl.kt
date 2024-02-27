package com.example.tmdbclone.data.remote.repository

import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.CelebritiesModelDto
import com.example.tmdbclone.data.remote.service.CelebritiesService
import com.example.tmdbclone.domain.repository.CelebritiesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CelebritiesRepositoryImpl @Inject constructor(private val celebritiesService: CelebritiesService) :
    CelebritiesRepository {

    override suspend fun fetchPopularCelebrities(): Flow<Resource<List<CelebritiesModelDto.Result>>> =
        flow {
            try {

                emit(Resource.Loading(true))

                val response = celebritiesService.fetchPopularCelebrities()

                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        emit(Resource.Success(body.results))
                    }
                } else {
                    emit(Resource.Error("Something Went Wrong !"))
                }
            } catch (e: Exception) {

                emit(Resource.Error("Something Went Wrong !"))

            }
        }

    override suspend fun fetchTrendingCelebrities(): Flow<Resource<List<CelebritiesModelDto.Result>>> =
        flow {
            try {

                emit(Resource.Loading(true))

                val response = celebritiesService.fetchTrendingCelebrities()

                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        emit(Resource.Success(body.results))
                    }
                } else {
                    emit(Resource.Error("Something Went Wrong !"))
                }
            } catch (e: Exception) {

                emit(Resource.Error("Something Went Wrong !"))

            }
        }


}