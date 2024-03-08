package com.example.tmdbclone.data.remote.repository

import android.util.Log
import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.PopularMovieDTO
import com.example.tmdbclone.data.remote.service.MoviesService
import com.example.tmdbclone.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(private val moviesService: MoviesService) :
    MoviesRepository {

    private inline fun <reified T> fetchFlow(
        crossinline block: suspend () -> Response<T>
    ): Flow<Resource<T>> = flow {
        try {
            emit(Resource.Loading(true))
            val response = block()
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Resource.Success(it))
                }
            } else {
                emit(Resource.Error("Something Went Wrong !"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
            Log.d("RequestBodyVideosExec", "fetchMovieDetails: ${e.message}")
        }
    }

    override suspend fun fetchPopularMovies(): Flow<Resource<List<PopularMovieDTO.MovieModelDto>>> =
        flow {
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

    override suspend fun fetchUpcomingMovies(): Flow<Resource<List<PopularMovieDTO.MovieModelDto>>> =
        flow {
            try {

                emit(Resource.Loading(true))

                val response = moviesService.fetchUpcomingMovies()

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