package com.example.tmdbclone.domain.usecase

import com.example.tmdbclone.domain.repository.MoviesRepository
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke() = moviesRepository.fetchMovies()

}