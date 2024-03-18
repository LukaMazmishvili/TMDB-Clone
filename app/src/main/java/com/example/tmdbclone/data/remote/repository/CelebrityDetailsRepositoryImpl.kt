package com.example.tmdbclone.data.remote.repository

import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.fetchFlow
import com.example.tmdbclone.data.remote.mapper.toCelebrityDetailsModel
import com.example.tmdbclone.data.remote.mapper.toMovieModel
import com.example.tmdbclone.data.remote.model.CelebrityDetailsModelDto
import com.example.tmdbclone.data.remote.model.MoviesDTO
import com.example.tmdbclone.data.remote.service.CelebrityDetailsService
import com.example.tmdbclone.domain.model.CelebrityDetailsModel
import com.example.tmdbclone.domain.model.MovieModel
import com.example.tmdbclone.domain.repository.CelebrityDetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CelebrityDetailsRepositoryImpl @Inject constructor(private val celebrityDetailsService: CelebrityDetailsService) :
    CelebrityDetailsRepository {

    override suspend fun fetchCelebrityDetails(personId: Int): Flow<Resource<CelebrityDetailsModel>> =
        fetchFlow<CelebrityDetailsModelDto, CelebrityDetailsModel>(
            block = { celebrityDetailsService.fetchCelebrityDetails(personId) },
            mapper = {
                it.toCelebrityDetailsModel()
            }
        )

    override suspend fun fetchCelebrityMovieCredits(personId: Int): Flow<Resource<MovieModel>> =

        fetchFlow<MoviesDTO, MovieModel>(
            block = { celebrityDetailsService.fetchCelebrityMovieCredits(personId) },
            mapper = {
                it.toMovieModel(genres)
            }
        )

    override suspend fun fetchCelebrityTvShowCredits(personId: Int): Flow<Resource<MovieModel>> =
        fetchFlow<MoviesDTO, MovieModel>(
            block = { celebrityDetailsService.fetchCelebrityTvShowsCredits(personId) },
            mapper = {
                it.toMovieModel(genres)
            }
        )

}