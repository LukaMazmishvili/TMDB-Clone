package com.example.tmdbclone.data.remote.model

import com.google.gson.annotations.SerializedName

data class MovieDetailsModelDto(
    val adult: Boolean?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("belongs_to_collection")
    val belongsToCollection: Collection?,
    val budget: Long?,
    val genres: List<GenresModelDto>?,
    val homePage: String?,
    val id: Int?,
    @SerializedName("imdb_id")
    val imdbId: String?,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("original_title")
    val originalTitle: String?,
    val overview: String?,
    val popularity: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("production_companies")
    val productionCompanies: List<Company>?,
    @SerializedName("production_countries")
    val productionCountries: List<Country>,
    @SerializedName("release_date")
    val releaseDate: String?,
    val revenue: Long?,
    val runtime: Long?,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<Language>,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    @SerializedName("vote_average")
    val voteAverage: Float?,
    @SerializedName("vote_count")
    val voteCount: Long?

) {

    data class Collection(
        val id: Int?,
        val name: String?,
        @SerializedName("poster_path")
        val posterPath: String?,
        @SerializedName("backdrop_path")
        val backdropPath: String?
    )

    data class Company(
        val id: Int?,
        @SerializedName("logo_path")
        val logoPath: String?,
        val name: String?,
        @SerializedName("original_country")
        val originalCountry: String?
    )

    data class Country(
        @SerializedName("iso_3166_1")
        val iso31661: String?,
        val name: String?
    )

    data class Language(
        @SerializedName("english_name")
        val englishName: String?,
        @SerializedName("iso_639_1")
        val iso6391: String?,
        val name: String
    )
}


