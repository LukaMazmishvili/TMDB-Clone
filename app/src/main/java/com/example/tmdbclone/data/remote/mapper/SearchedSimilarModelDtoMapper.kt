package com.example.tmdbclone.data.remote.mapper

import com.example.tmdbclone.data.remote.model.MoviesDTO
import com.example.tmdbclone.data.remote.model.SearchSimilarModelDto
import com.example.tmdbclone.domain.model.MovieModel
import com.example.tmdbclone.domain.model.SearchSimilarModel
import com.example.tmdbclone.extension.toGender
import com.example.tmdbclone.extension.toGenre

fun SearchSimilarModelDto.toSearchSimilarModel(genresList: Map<Int, String>): SearchSimilarModel {
    return SearchSimilarModel(
        page = page,
        results = mapResult(genresList, this.results),
    )
}

private fun mapResult(
    genresList: Map<Int, String>,
    list: List<SearchSimilarModelDto.SimilarSearches>?
): List<SearchSimilarModel.SimilarSearches> {
    return list?.map {
        SearchSimilarModel.SimilarSearches(
            adult = it.adult,
            backdropPath = it.backdropPath,
            genreIds = genresList.toGenre(it.genreIds),
            id = it.id,
            originalLanguage = it.originalLanguage,
            originalTitle = it.originalTitle,
            originalName = it.originalName,
            overview = it.overview,
            popularity = it.popularity,
            posterPath = it.posterPath,
            mediaType = it.mediaType,
            releaseDate = it.releaseDate,
            title = it.title,
            video = it.video,
            voteAverage = it.voteAverage,
            voteCount = it.voteCount,
            name = it.name,
            gender = it.gender?.toGender(),
            castId = it.castId,
            character = it.character,
            creditId = it.creditId,
            order = it.order,
            knownForDepartment = it.knownForDepartment,
            profilePath = it.profilePath,
            knownFor = mapKnownFor(genresList, it.knownFor)
        )
    } ?: emptyList()
}

private fun mapKnownFor(genresList: Map<Int, String>, list: List<MoviesDTO>?): List<MovieModel> {
    return list?.map {
        it.toMovieModel(genresList)
    } ?: emptyList()
}