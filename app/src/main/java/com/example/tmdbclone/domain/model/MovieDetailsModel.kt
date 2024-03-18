package com.example.tmdbclone.domain.model

import com.example.tmdbclone.data.remote.model.GenresModelDto

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
    val productionCountries: List<Country>,
    val releaseDate: String?,
    val revenue: Long?,
    val runtime: Long?,
    val spokenLanguages: List<Language>,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Float?,
    val voteCount: Long?

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
        val name: String
    )
}
