package com.example.tmdbclone.data.remote.mapper

import com.example.tmdbclone.data.remote.model.MovieDetailsModelDto
import com.example.tmdbclone.domain.model.MovieDetailsModel
import com.example.tmdbclone.extension.toGenre


fun MovieDetailsModelDto.toMovieDetailsModel(genresList: Map<Int, String>): MovieDetailsModel {
    return MovieDetailsModel(
        adult = this.adult,
        backdropPath = this.backdropPath,
        belongsToCollection = MovieDetailsModel.Collection(
            this.belongsToCollection?.id,
            this.belongsToCollection?.name,
            this.belongsToCollection?.posterPath,
            this.belongsToCollection?.backdropPath
        ),
        budget = this.budget,
        genres = this.genres,
        homePage = this.homePage,
        id = this.id,
        imdbId = this.imdbId,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        productionCompanies = mapProductionCompanies(this.productionCompanies),
        productionCountries = mapProductionCountries(this.productionCountries),
        releaseDate = this.releaseDate,
        revenue = this.revenue,
        runtime = this.runtime,
        spokenLanguages = mapSpokenLanguages(this.spokenLanguages),
        status = this.status,
        tagline = this.tagline,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}

private fun mapProductionCompanies(list: List<MovieDetailsModelDto.Company>?): List<MovieDetailsModel.Company> {
    return list?.map {
        MovieDetailsModel.Company(
            id = it.id,
            logoPath = it.logoPath,
            name = it.name,
            originalCountry = it.originalCountry
        )
    } ?: emptyList()
}

private fun mapProductionCountries(list: List<MovieDetailsModelDto.Country>?): List<MovieDetailsModel.Country> {
    return list?.map {
        MovieDetailsModel.Country(
            iso31661 = it.iso31661,
            name = it.name
        )
    } ?: emptyList()
}

private fun mapSpokenLanguages(list: List<MovieDetailsModelDto.Language>?): List<MovieDetailsModel.Language> {
    return list?.map {
        MovieDetailsModel.Language(
            englishName = it.englishName,
            iso6391 = it.iso6391,
            name = it.name
        )
    } ?: emptyList()
}