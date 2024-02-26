package com.example.tmdbclone.data.remote.repository

import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.PopularMovieDTO
import com.example.tmdbclone.data.remote.service.MoviesService
import com.example.tmdbclone.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(private val moviesService: MoviesService) :
    MoviesRepository {

    override suspend fun fetchMovies(): Flow<Resource<List<PopularMovieDTO.MovieModelDto>>> = flow {
        try {

            emit(Resource.Loading(true))

            val response = moviesService.fetchPopularMovies()

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

    override suspend fun fetchNowPlaying(): Flow<Resource<List<PopularMovieDTO.MovieModelDto>>> =
        flow {
            try {

                emit(Resource.Loading(true))

                val response = moviesService.fetchNowPlaying()

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

    override suspend fun fetchTrendingMovies(): Flow<Resource<List<PopularMovieDTO.MovieModelDto>>> =
        flow {
            try {

                emit(Resource.Loading(true))

                val response = moviesService.fetchTrendingMovies()

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


    override suspend fun fetchTopRatedMovies(): Flow<Resource<List<PopularMovieDTO.MovieModelDto>>> =
        flow {
            try {

                emit(Resource.Loading(true))

                val response = moviesService.fetchTopRatedMovies()

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