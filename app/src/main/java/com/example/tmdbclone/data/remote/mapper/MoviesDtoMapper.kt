package com.example.tmdbclone.data.remote.mapper

import com.example.tmdbclone.data.remote.model.MoviesDTO
import com.example.tmdbclone.domain.model.MovieModel

fun MoviesDTO.MovieModelDto.toMovieModel(genreIds: List<String>): MovieModel.Movie {
    return MovieModel.Movie(
        adult = this.adult,
        backdropPath = this.backdropPath,
        genreIds = genreIds,
        id = this.id,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        originalName = this.originalName,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        mediaType = this.mediaType,
        releaseDate = this.releaseDate,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )

}