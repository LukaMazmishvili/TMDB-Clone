package com.example.tmdbclone.domain.repository

import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.PopularMovieDTO
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    // todo სახელი არ მომწონს :დ
    suspend fun fetchMovies(): Flow<Resource<List<PopularMovieDTO.MovieModelDto>>>
}