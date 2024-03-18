package com.example.tmdbclone.domain.repository

import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.CelebritiesModelDto
import com.example.tmdbclone.domain.model.CelebritiesModel
import kotlinx.coroutines.flow.Flow

interface CelebritiesRepository {

    suspend fun fetchPopularCelebrities(): Flow<Resource<CelebritiesModel>>

    suspend fun fetchTrendingCelebrities(): Flow<Resource<CelebritiesModel>>
}