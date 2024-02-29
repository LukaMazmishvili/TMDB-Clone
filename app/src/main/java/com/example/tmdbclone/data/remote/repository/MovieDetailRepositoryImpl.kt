package com.example.tmdbclone.data.remote.repository

import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.CelebritiesModelDto
import com.example.tmdbclone.data.remote.model.MovieDetailsModelDto
import com.example.tmdbclone.data.remote.model.PopularMovieDTO
import com.example.tmdbclone.data.remote.model.VideoModelDto
import com.example.tmdbclone.data.remote.service.MovieDetailService
import com.example.tmdbclone.domain.repository.MovieDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(private val movieDetailService: MovieDetailService) :
    MovieDetailRepository {
    override suspend fun fetchMovieDetails(): Flow<Resource<MovieDetailsModelDto>> =
        flow {
            try {

                emit(Resource.Loading(true))

                val response = movieDetailService.fetchMovieDetails()

                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        emit(Resource.Success(body))
                    }
                } else {
                    emit(Resource.Error("Something Went Wrong !"))
                }
            } catch (e: Exception) {

                emit(Resource.Error("Something Went Wrong !"))

            }
        }

    override suspend fun fetchMovieCast(): Flow<Resource<CelebritiesModelDto>> =
        flow {
            try {

                emit(Resource.Loading(true))

                val response = movieDetailService.fetchMovieCast()

                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        emit(Resource.Success(body))
                    }
                } else {
                    emit(Resource.Error("Something Went Wrong !"))
                }
            } catch (e: Exception) {

                emit(Resource.Error("Something Went Wrong !"))

            }
        }

    override suspend fun fetchMovieVideos(): Flow<Resource<List<VideoModelDto>>> =
        flow {
            try {

                emit(Resource.Loading(true))

                val response = movieDetailService.fetchMovieVideos()

                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        emit(Resource.Success(body))
                    }
                } else {
                    emit(Resource.Error("Something Went Wrong !"))
                }
            } catch (e: Exception) {

                emit(Resource.Error("Something Went Wrong !"))

            }
        }

    override suspend fun fetchMovieRecommend(): Flow<Resource<PopularMovieDTO>> =
        flow {
            try {

                emit(Resource.Loading(true))

                val response = movieDetailService.fetchMovieRecommendation()

                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        emit(Resource.Success(body))
                    }
                } else {
                    emit(Resource.Error("Something Went Wrong !"))
                }
            } catch (e: Exception) {

                emit(Resource.Error("Something Went Wrong !"))

            }
        }

    override suspend fun fetchMovieSimilar(): Flow<Resource<PopularMovieDTO>> =
        flow {
            try {

                emit(Resource.Loading(true))

                val response = movieDetailService.fetchMovieSimilar()

                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        emit(Resource.Success(body))
                    }
                } else {
                    emit(Resource.Error("Something Went Wrong !"))
                }
            } catch (e: Exception) {

                emit(Resource.Error("Something Went Wrong !"))

            }
        }
}