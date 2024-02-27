package com.example.tmdbclone.data.remote.repository

import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.PopularMovieDTO
import com.example.tmdbclone.data.remote.service.TvShowsService
import com.example.tmdbclone.domain.repository.TvShowsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TvShowsRepositoryImpl @Inject constructor(private val tvShowsService: TvShowsService) :
    TvShowsRepository {

    override suspend fun fetchAiringTodayTvShows(): Flow<Resource<List<PopularMovieDTO.MovieModelDto>>> =
        flow {
            try {

                emit(Resource.Loading(true))

                val response = tvShowsService.fetchAiringTodayTvShows()

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

    override suspend fun fetchTrendingTvShows(): Flow<Resource<List<PopularMovieDTO.MovieModelDto>>> =
        flow {
            try {

                emit(Resource.Loading(true))

                val response = tvShowsService.fetchTrendingTvShows()

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

    override suspend fun fetchTopRatedTvShows(): Flow<Resource<List<PopularMovieDTO.MovieModelDto>>> =
        flow {
            try {

                emit(Resource.Loading(true))

                val response = tvShowsService.fetchTopRatedTvShows()

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

    override suspend fun fetchPopularTvShows(): Flow<Resource<List<PopularMovieDTO.MovieModelDto>>> =
        flow {
            try {

                emit(Resource.Loading(true))

                val response = tvShowsService.fetchPopularTvShows() // todo change this

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