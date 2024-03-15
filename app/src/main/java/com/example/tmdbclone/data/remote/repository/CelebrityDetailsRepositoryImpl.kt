package com.example.tmdbclone.data.remote.repository

import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.fetchFlow
import com.example.tmdbclone.data.remote.model.CelebrityDetailsModelDto
import com.example.tmdbclone.data.remote.model.PopularMovieDTO
import com.example.tmdbclone.data.remote.service.CelebrityDetailsService
import com.example.tmdbclone.domain.repository.CelebrityDetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CelebrityDetailsRepositoryImpl @Inject constructor(private val celebrityDetailsService: CelebrityDetailsService) :
    CelebrityDetailsRepository {

    override suspend fun fetchCelebrityDetails(personId: Int): Flow<Resource<CelebrityDetailsModelDto>> =
        fetchFlow {
            celebrityDetailsService.fetchCelebrityDetails(personId)
        }

    override suspend fun fetchCelebrityMovieCredits(personId: Int): Flow<Resource<List<PopularMovieDTO>>> =
        fetchFlow {
            celebrityDetailsService.fetchCelebrityMovieCredits(personId)
        }

    override suspend fun fetchCelebrityTvShowCredits(personId: Int): Flow<Resource<List<PopularMovieDTO>>> =
        fetchFlow {
            celebrityDetailsService.fetchCelebrityTvShowsCredits(personId)
        }

}