package com.example.tmdbclone.data.remote.repository

import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.fetchFlow
import com.example.tmdbclone.data.remote.mapper.toMovieModel
import com.example.tmdbclone.data.remote.model.MoviesDTO
import com.example.tmdbclone.data.remote.service.GenresService
import com.example.tmdbclone.data.remote.service.MoviesService
import com.example.tmdbclone.domain.model.MovieModel
import com.example.tmdbclone.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


val genres = mutableMapOf<Int, String>()

class MoviesRepositoryImpl @Inject constructor(
    private val moviesService: MoviesService,
    private val genresService: GenresService
) :
    MoviesRepository {

    init {
//        CoroutineScope(Dispatchers.Main).launch {
//            genres()
//        }
    }

    override suspend fun genres() {
        val responseMovieGenres = genresService.fetchMovieGenres()
        val responseTvShowGenres = genresService.fetchTvShowGenres()

        if (responseMovieGenres.isSuccessful && responseTvShowGenres.isSuccessful) {
            val bodyMovieGenres = responseMovieGenres.body()
            val bodyTvShowsGenres = responseTvShowGenres.body()
            bodyMovieGenres?.genres?.forEach {
                it.id?.let { id ->
                    it.name?.let { name ->
                        genres[id] = name
                    }
                }
            }
            bodyTvShowsGenres?.genres?.forEach {
                it.id?.let { id ->
                    it.name?.let { name ->
                        genres[id] = name
                    }
                }
            }
        }
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