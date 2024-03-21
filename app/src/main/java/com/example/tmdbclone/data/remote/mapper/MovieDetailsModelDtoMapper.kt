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
        voteCount = this.voteCount,
        createdBy = emptyList(),
        episodeRunTime = this.episodeRunTime,
        firstAirDate = this.firstAirDate,
        homepage = this.homepage,
        inProduction = this.inProduction,
        languages = this.languages,
        lastAirDate = this.lastAirDate,
        lastEpisodeToAir = MovieDetailsModel.LastEpisodeToAir(
            airDate = this.lastEpisodeToAir?.airDate,
            episodeNumber = this.lastEpisodeToAir?.episodeNumber,
            episodeType = this.lastEpisodeToAir?.episodeType,
            id = this.lastEpisodeToAir?.id,
            name = this.lastEpisodeToAir?.name,
            overview = this.lastEpisodeToAir?.overview,
            productionCode = this.lastEpisodeToAir?.productionCode,
            runtime = this.lastEpisodeToAir?.runtime,
            seasonNumber = this.lastEpisodeToAir?.seasonNumber,
            showId = this.lastEpisodeToAir?.showId,
            stillPath = this.lastEpisodeToAir?.stillPath,
            voteAverage = this.lastEpisodeToAir?.voteAverage,
            voteCount = this.lastEpisodeToAir?.voteCount
        ),
        name = this.name,
        networks = mapNetworks(this.networks),
        nextEpisodeToAir = this.nextEpisodeToAir,
        numberOfEpisodes = this.numberOfEpisodes,
        numberOfSeasons = this.numberOfSeasons,
        originCountry = this.originCountry,
        originalName = this.originalName,
        seasons = mapSeason(this.seasons),
        type = this.type
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

private fun mapNetworks(list: List<MovieDetailsModelDto.Network>?): List<MovieDetailsModel.Network> {
    return list?.map {
        MovieDetailsModel.Network(
            id = it.id,
            logoPath = it.logoPath,
            name = it.name,
            originCountry = it.originCountry
        )
    } ?: emptyList()
}

private fun mapSeason(list: List<MovieDetailsModelDto.Season>?): List<MovieDetailsModel.Season> {
    return list?.map {
        MovieDetailsModel.Season(
            airDate = it.airDate,
            episodeCount = it.episodeCount,
            id = it.id,
            name = it.name,
            overview = it.overview,
            posterPath = it.posterPath,
            seasonNumber = it.seasonNumber,
            voteAverage = it.voteAverage
        )
    } ?: emptyList()
}