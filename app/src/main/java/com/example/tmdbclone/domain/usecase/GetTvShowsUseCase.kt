package com.example.tmdbclone.domain.usecase

import com.example.tmdbclone.domain.repository.TvShowsRepository
import javax.inject.Inject

class GetTvShowsUseCase @Inject constructor(private val tvShowsRepository: TvShowsRepository) {

    suspend fun getAiringTodayTvShows() = tvShowsRepository.fetchAiringTodayTvShows()
    suspend fun getTrendingTvShows() = tvShowsRepository.fetchTrendingTvShows()
    suspend fun getTopRatedTvShows() = tvShowsRepository.fetchTopRatedTvShows()
    suspend fun getPopularTvShows() = tvShowsRepository.fetchPopularTvShows()
}