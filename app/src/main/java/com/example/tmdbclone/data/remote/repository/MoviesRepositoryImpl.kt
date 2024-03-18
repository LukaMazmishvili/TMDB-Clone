package com.example.tmdbclone.data.remote.repository

import android.util.Log
import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.fetchFlow
import com.example.tmdbclone.data.remote.mapper.toMovieModel
import com.example.tmdbclone.data.remote.model.MoviesDTO
import com.example.tmdbclone.data.remote.service.GenresService
import com.example.tmdbclone.data.remote.service.MoviesService
import com.example.tmdbclone.domain.model.MovieModel
import com.example.tmdbclone.domain.model.MovieModel.Movie
import com.example.tmdbclone.domain.repository.MoviesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import javax.inject.Inject


val genres = mutableMapOf<Int, String>()

class MoviesRepositoryImpl @Inject constructor(
    private val moviesService: MoviesService,
    private val genresService: GenresService
) :
    MoviesRepository {

    init {
        CoroutineScope(Dispatchers.IO).launch {
            genres()
        }
    }

    suspend fun genres(): Map<Int, String> {
        val response = genresService.fetchMovieGenres()

        if (response.isSuccessful) {
            val body = response.body()
            body?.genres?.forEach {
                it.id?.let { id ->
                    it.name?.let { name ->
                        genres[id] = name
                    }
                }
            }
        }
        return genres
    }

    override suspend fun fetchPopularMovies(page: Int): Flow<Resource<MovieModel>> {

        return fetchFlow<MoviesDTO, MovieModel>(
            block = { moviesService.fetchPopularMovies(page) },
            mapper = {
                it.toMovieModel(genres)
            }
        )
    }

    override suspend fun fetchNowPlaying(): Flow<Resource<MovieModel>> =
        fetchFlow<MoviesDTO, MovieModel>(
            block = { moviesService.fetchNowPlaying() },
            mapper = {
                it.toMovieModel(genres)
            }
        )

    override suspend fun fetchTrendingMovies(): Flow<Resource<MovieModel>> =
        fetchFlow<MoviesDTO, MovieModel>(
            block = { moviesService.fetchTrendingMovies() },
            mapper = {
                it.toMovieModel(genres)
            }
        )

    override suspend fun fetchTopRatedMovies(): Flow<Resource<MovieModel>> =
        fetchFlow<MoviesDTO, MovieModel>(
            block = { moviesService.fetchTopRatedMovies() },
            mapper = {
                it.toMovieModel(genres)
            }
        )

    override suspend fun fetchUpcomingMovies(): Flow<Resource<MovieModel>> =
        fetchFlow<MoviesDTO, MovieModel>(
            block = { moviesService.fetchUpcomingMovies() },
            mapper = {
                it.toMovieModel(genres)
            }
        )

}