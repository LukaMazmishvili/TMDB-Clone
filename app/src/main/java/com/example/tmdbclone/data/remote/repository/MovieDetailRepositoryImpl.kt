package com.example.tmdbclone.data.remote.repository

import android.util.Log
import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.CelebritiesModelDto
import com.example.tmdbclone.data.remote.model.MovieDetailsModelDto
import com.example.tmdbclone.data.remote.model.MoviesDTO
import com.example.tmdbclone.data.remote.model.VideoModelDto
import com.example.tmdbclone.data.remote.service.GenresService
import com.example.tmdbclone.data.remote.service.MovieDetailService
import com.example.tmdbclone.domain.repository.MovieDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(
    private val movieDetailService: MovieDetailService,
    private val genresService: GenresService
) :
    MovieDetailRepository {

    override suspend fun fetchMovieGenres(): Map<Int, String> {
        try {

            val genresMap = mutableMapOf<Int, String>()

            val response = genresService.fetchMovieGenres()

            if (response.isSuccessful) {

                val body = response.body()!!
                for (i in body) {
                    if (i.id != null && i.name != null)
                        genresMap[i.id] = i.name
                }

                return genresMap
            }
        } catch (e: Exception) {
            Log.d("ExceptionFetchingGenres", "fetchGenres: ${e.message}")
        }

        return mapOf()

    }

    override suspend fun fetchMovieDetails(movieId: Int): Flow<Resource<MovieDetailsModelDto>> =
        flow {
            try {

                emit(Resource.Loading(true))

                val response = movieDetailService.fetchMovieDetails(movieId)

                Log.d("RequestBody", "fetchMovieDetails: ${response.code()}")
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

//                Log.d("RequestBody", "fetchMovieDetails: ${e.message}")
            }
        }

    override suspend fun fetchMovieCast(movieId: Int): Flow<Resource<CelebritiesModelDto>> =
        flow {
            try {

                emit(Resource.Loading(true))

                val response = movieDetailService.fetchMovieCast(movieId)

                if (response.isSuccessful) {
                    val body = response.body()

                    body?.let {
                        emit(Resource.Success(body))
                    }
                } else {
                    emit(Resource.Error("Something Went Wrong !"))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }

    override suspend fun fetchMovieVideos(movieId: Int): Flow<Resource<VideoModelDto>> =
        flow {
            try {

                emit(Resource.Loading(true))

                val response = movieDetailService.fetchMovieVideos(movieId)

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
                Log.d("RequestBodyVideosExec", "fetchMovieDetails: ${e.message}")

            }
        }

    override suspend fun fetchMovieRecommend(movieId: Int): Flow<Resource<MoviesDTO>> =
        flow {
            try {

                emit(Resource.Loading(true))

                val response = movieDetailService.fetchMovieRecommendation(movieId)

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

    override suspend fun fetchMovieSimilar(movieId: Int): Flow<Resource<MoviesDTO>> =
        flow {
            try {

                emit(Resource.Loading(true))

                val response = movieDetailService.fetchMovieSimilar(movieId)

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