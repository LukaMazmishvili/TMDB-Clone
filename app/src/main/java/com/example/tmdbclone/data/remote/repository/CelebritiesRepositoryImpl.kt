package com.example.tmdbclone.data.remote.repository

import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.fetchFlow
import com.example.tmdbclone.data.remote.mapper.toCelebritiesModel
import com.example.tmdbclone.data.remote.model.CelebritiesModelDto
import com.example.tmdbclone.data.remote.service.CelebritiesService
import com.example.tmdbclone.domain.model.CelebritiesModel
import com.example.tmdbclone.domain.repository.CelebritiesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CelebritiesRepositoryImpl @Inject constructor(private val celebritiesService: CelebritiesService) :
    CelebritiesRepository {

    override suspend fun fetchPopularCelebrities(): Flow<Resource<CelebritiesModel>> =

        fetchFlow<CelebritiesModelDto, CelebritiesModel>(
            block = { celebritiesService.fetchPopularCelebrities() },
            mapper = {
                it.toCelebritiesModel()
            }
        )

    override suspend fun fetchTrendingCelebrities(): Flow<Resource<CelebritiesModel>> =

        fetchFlow<CelebritiesModelDto, CelebritiesModel>(
            block = { celebritiesService.fetchTrendingCelebrities() },
            mapper = {
                it.toCelebritiesModel()
            }
        )

}