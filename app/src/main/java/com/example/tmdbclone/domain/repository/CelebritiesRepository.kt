package com.example.tmdbclone.domain.repository

import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.CelebritiesModelDto
import com.example.tmdbclone.data.remote.model.PopularMovieDTO
import kotlinx.coroutines.flow.Flow

interface CelebritiesRepository {

    suspend fun fetchPopularCelebrities(): Flow<Resource<List<CelebritiesModelDto.Result>>>

    suspend fun fetchTrendingCelebrities(): Flow<Resource<List<CelebritiesModelDto.Result>>>
}