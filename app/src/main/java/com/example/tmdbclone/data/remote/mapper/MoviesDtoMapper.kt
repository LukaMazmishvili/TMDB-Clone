package com.example.tmdbclone.data.remote.mapper

import com.example.tmdbclone.data.remote.model.MoviesDTO
import com.example.tmdbclone.domain.model.MovieModel
import com.example.tmdbclone.extension.toGenre


fun MoviesDTO.toMovieModel(genreIds: Map<Int, String>): MovieModel {
    return MovieModel(
        dates = MovieModel.Dates(
            this.dates?.maximum ?: "AS",
            this.dates?.minimum ?: "AS"
        ),
        page = this.page,
        results = mapMovie(genreIds, this.results)
    )
}

fun mapMovie(
    genreIds: Map<Int, String>,
    list: List<MoviesDTO.MovieModelDto>?
): List<MovieModel.Movie> {
    return list?.map { model ->
        MovieModel.Movie(
            adult = model.adult,
            backdropPath = model.backdropPath,
            genreIds = genreIds.toGenre(model.genreIds),
            id = model.id,
            originalLanguage = model.originalLanguage,
            originalTitle = model.originalTitle,
            originalName = model.originalName,
            overview = model.overview,
            popularity = model.popularity,
            posterPath = model.posterPath,
            mediaType = model.mediaType,
            releaseDate = model.releaseDate,
            title = model.title,
            video = model.video,
            voteAverage = model.voteAverage,
            voteCount = model.voteCount
        )
    } ?: emptyList()
}