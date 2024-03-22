package com.example.tmdbclone.data.remote.repository

import android.util.Log
import com.example.tmdbclone.common.MediaTypes
import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.fetchFlow
import com.example.tmdbclone.data.remote.mapper.toCelebritiesModel
import com.example.tmdbclone.data.remote.mapper.toMovieDetailsModel
import com.example.tmdbclone.data.remote.mapper.toMovieModel
import com.example.tmdbclone.data.remote.model.CelebritiesModelDto
import com.example.tmdbclone.data.remote.model.FavouriteResponseDto
import com.example.tmdbclone.data.remote.model.MovieDetailsModelDto
import com.example.tmdbclone.data.remote.model.MoviesDTO
import com.example.tmdbclone.data.remote.model.VideoModelDto
import com.example.tmdbclone.data.remote.service.GenresService
import com.example.tmdbclone.data.remote.service.IsFavouriteService
import com.example.tmdbclone.data.remote.service.MovieDetailService
import com.example.tmdbclone.data.remote.service.TvShowDetailsService
import com.example.tmdbclone.domain.model.CelebritiesModel
import com.example.tmdbclone.domain.model.MovieDetailsModel
import com.example.tmdbclone.domain.model.MovieModel
import com.example.tmdbclone.domain.repository.MovieDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(
    private val movieDetailService: MovieDetailService,
    private val tvShowDetailsService: TvShowDetailsService,
    private val isFavouriteService: IsFavouriteService,
    private val genresService: GenresService
) :
    MovieDetailRepository {

    override suspend fun checkIfFavourite(movieId: Int, token: String): Flow<Resource<FavouriteResponseDto>> =
        flow {
            try {

                val response = isFavouriteService.checkFavourite(movieId, bearer = token)

                if (response.isSuccessful) {
                    val body = response.body()
                    emit(Resource.Success(body))
                } else {
                    emit(Resource.Error(response.errorBody()?.string() ?: "Something went wrong !"))
                }
            } catch (e: HttpException) {
                Log.d("CheckHttpException", "fetchFlow: ${e.message()}")
                emit(Resource.Error(e.message(), e.code()))
            } catch (e: Exception) {
                Log.d("CheckException", "fetchFlow: ${e.message.toString()}")
                emit(Resource.Error(e.message.toString()))
            }
        }

    override suspend fun fetchMovieGenres(): Map<Int, String> {
        try {

            val genresMap = mutableMapOf<Int, String>()

            val response = genresService.fetchMovieGenres()

            if (response.isSuccessful) {

                val body = response.body()!!
                for (i in body.genres) {
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

    override suspend fun fetchMovieDetails(
        movieId: Int,
        mediaType: MediaTypes
    ): Flow<Resource<MovieDetailsModel>> =

        fetchFlow<MovieDetailsModelDto, MovieDetailsModel>(
            block = {
                if (mediaType == MediaTypes.Movie) {
                    movieDetailService.fetchMovieDetails(movieId)
                } else {
                    tvShowDetailsService.fetchTvShowDetails(movieId)
                }
            },
            mapper = {
                it.toMovieDetailsModel(genres)
            }
        )

    override suspend fun fetchMovieCast(movieId: Int): Flow<Resource<CelebritiesModel>> =

        fetchFlow<CelebritiesModelDto, CelebritiesModel>(
            block = { movieDetailService.fetchMovieCast(movieId) },
            mapper = {
                it.toCelebritiesModel()
            }
        )

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

    override suspend fun fetchMovieRecommend(movieId: Int): Flow<Resource<MovieModel>> =

        fetchFlow<MoviesDTO, MovieModel>(
            block = { movieDetailService.fetchMovieRecommendation(movieId) },
            mapper = {
                it.toMovieModel(genres)
            }
        )

    override suspend fun fetchMovieSimilar(movieId: Int): Flow<Resource<MovieModel>> =

        fetchFlow<MoviesDTO, MovieModel>(
            block = { movieDetailService.fetchMovieSimilar(movieId) },
            mapper = {
                it.toMovieModel(genres)
            }
        )
}