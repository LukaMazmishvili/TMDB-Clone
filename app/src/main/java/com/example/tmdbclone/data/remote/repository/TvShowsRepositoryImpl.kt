package com.example.tmdbclone.data.remote.repository

import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.MoviesDTO
import com.example.tmdbclone.data.remote.service.TvShowsService
import com.example.tmdbclone.domain.repository.TvShowsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TvShowsRepositoryImpl @Inject constructor(private val tvShowsService: TvShowsService) :
    TvShowsRepository {

    override suspend fun fetchAiringTodayTvShows(): Flow<Resource<List<MoviesDTO.MovieModelDto>>> =
        flow {
            try {

                emit(Resource.Loading(true))

                val response = tvShowsService.fetchAiringTodayTvShows("1")

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

    override suspend fun fetchTrendingTvShows(): Flow<Resource<List<MoviesDTO.MovieModelDto>>> =
        flow {
            try {

                emit(Resource.Loading(true))

                val response = tvShowsService.fetchTrendingTvShows("1")

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

    override suspend fun fetchTopRatedTvShows(): Flow<Resource<List<MoviesDTO.MovieModelDto>>> =
        flow {
            try {

                emit(Resource.Loading(true))

                val response = tvShowsService.fetchTopRatedTvShows("1")

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

    override suspend fun fetchPopularTvShows(): Flow<Resource<List<MoviesDTO.MovieModelDto>>> =
        flow {
            try {

                emit(Resource.Loading(true))

                val response = tvShowsService.fetchPopularTvShows("1") // todo change this

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