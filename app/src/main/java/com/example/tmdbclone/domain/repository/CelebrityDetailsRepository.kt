package com.example.tmdbclone.domain.repository

import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.CelebrityDetailsModelDto
import com.example.tmdbclone.data.remote.model.MoviesDTO
import kotlinx.coroutines.flow.Flow

interface CelebrityDetailsRepository {

    suspend fun fetchCelebrityDetails(personId: Int): Flow<Resource<CelebrityDetailsModelDto>>
    suspend fun fetchCelebrityMovieCredits(personId: Int): Flow<Resource<List<MoviesDTO>>>
    suspend fun fetchCelebrityTvShowCredits(personId: Int): Flow<Resource<List<MoviesDTO>>>

}