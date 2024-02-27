package com.example.tmdbclone.domain.usecase

import com.example.tmdbclone.domain.repository.CelebritiesRepository
import javax.inject.Inject

class GetCelebritiesUseCase @Inject constructor(private val celebritiesRepository: CelebritiesRepository) {

    suspend fun getPopularCelebrities() = celebritiesRepository.fetchPopularCelebrities()

    suspend fun getTrendingCelebrities() = celebritiesRepository.fetchTrendingCelebrities()
}
