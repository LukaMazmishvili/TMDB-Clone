package com.example.tmdbclone.data.remote.repository

import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.fetchFlow
import com.example.tmdbclone.data.remote.mapper.toMovieModel
import com.example.tmdbclone.data.remote.model.MoviesDTO
import com.example.tmdbclone.data.remote.service.TvShowsService
import com.example.tmdbclone.domain.model.MovieModel
import com.example.tmdbclone.domain.repository.TvShowsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TvShowsRepositoryImpl @Inject constructor(private val tvShowsService: TvShowsService) :
    TvShowsRepository {

    override suspend fun fetchAiringTodayTvShows(): Flow<Resource<MovieModel>> =
        fetchFlow<MoviesDTO, MovieModel>(
            block = { tvShowsService.fetchAiringTodayTvShows("1") },
            mapper = {
                it.toMovieModel(genres)
            }
        )

    override suspend fun fetchTrendingTvShows(): Flow<Resource<MovieModel>> =
        fetchFlow<MoviesDTO, MovieModel>(
            block = { tvShowsService.fetchTrendingTvShows("1") },
            mapper = {
                it.toMovieModel(genres)
            }
        )

    override suspend fun fetchTopRatedTvShows(): Flow<Resource<MovieModel>> =
        fetchFlow<MoviesDTO, MovieModel>(
            block = { tvShowsService.fetchTopRatedTvShows("1") },
            mapper = {
                it.toMovieModel(genres)
            }
        )

    override suspend fun fetchPopularTvShows(): Flow<Resource<MovieModel>> =
        fetchFlow<MoviesDTO, MovieModel>(
            block = { tvShowsService.fetchPopularTvShows("1") },
            mapper = {
                it.toMovieModel(genres)
            }
        )
}