package com.example.tmdbclone.domain.repository

import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.CelebrityDetailsModelDto
import com.example.tmdbclone.data.remote.model.MoviesDTO
import com.example.tmdbclone.domain.model.CelebrityDetailsModel
import com.example.tmdbclone.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface CelebrityDetailsRepository {

    suspend fun fetchCelebrityDetails(personId: Int): Flow<Resource<CelebrityDetailsModel>>
    suspend fun fetchCelebrityMovieCredits(personId: Int): Flow<Resource<MovieModel>>
    suspend fun fetchCelebrityTvShowCredits(personId: Int): Flow<Resource<MovieModel>>

}