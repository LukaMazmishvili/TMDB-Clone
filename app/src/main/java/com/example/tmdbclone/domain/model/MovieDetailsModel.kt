package com.example.tmdbclone.domain.model

import com.example.tmdbclone.data.remote.model.GenresModelDto
import com.example.tmdbclone.data.remote.model.MovieDetailsModelDto
import com.google.gson.annotations.SerializedName

data class MovieDetailsModel(
    val adult: Boolean?,
    val backdropPath: String?,
    val belongsToCollection: Collection?,
    val budget: Long?,
    val genres: List<GenresModelDto.Genre>?,
    val homePage: String?,
    val id: Int?,
    val imdbId: String?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: String?,
    val posterPath: String?,
    val productionCompanies: List<Company>?,
    val productionCountries: List<Country>?,
    val releaseDate: String?,
    val revenue: Long?,
    val runtime: Long?,
    val spokenLanguages: List<Language>,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Float?,
    val voteCount: Long?,
    val createdBy: List<Any>?,
    val episodeRunTime: List<Int>?,
    val firstAirDate: String?,
    val homepage: String?,
    val inProduction: Boolean?,
    val languages: List<String>?,
    val lastAirDate: String?,
    val lastEpisodeToAir: LastEpisodeToAir?,
    val name: String?,
    val networks: List<Network>?,
    val nextEpisodeToAir: Any?,
    val numberOfEpisodes: Int?,
    val numberOfSeasons: Int?,
    val originCountry: List<String>?,
    val originalName: String?,
    val seasons: List<Season>?,
    val type: String?,

    ) {

    data class Collection(
        val id: Int?,
        val name: String?,
        val posterPath: String?,
        val backdropPath: String?
    )

    data class Company(
        val id: Int?,
        val logoPath: String?,
        val name: String?,
        val originalCountry: String?
    )

    data class Country(
        val iso31661: String?,
        val name: String?
    )

    data class Language(
        val englishName: String?,
        val iso6391: String?,
        val name: String?
    )


    data class LastEpisodeToAir(
        val airDate: String?,
        val episodeNumber: Int?,
        val episodeType: String?,
        val id: Int?,
        val name: String?,
        val overview: String?,
        val productionCode: String?,
        val runtime: Int?,
        val seasonNumber: Int?,
        val showId: Int?,
        val stillPath: Any?,
        val voteAverage: Int?,
        val voteCount: Int?
    )

    data class Network(
        val id: Int?,
        val logoPath: String?,
        val name: String?,
        val originCountry: String?
    )

    data class Season(
        val airDate: String?,
        val episodeCount: Int?,
        val id: Int?,
        val name: String?,
        val overview: String?,
        val posterPath: Any?,
        val seasonNumber: Int?,
        val voteAverage: Int?
    )
}
