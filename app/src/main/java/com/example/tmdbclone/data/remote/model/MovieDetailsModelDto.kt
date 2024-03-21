package com.example.tmdbclone.data.remote.model

import com.google.gson.annotations.SerializedName

data class MovieDetailsModelDto(
    val adult: Boolean?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("belongs_to_collection")
    val belongsToCollection: Collection?,
    val budget: Long?,
    val genres: List<GenresModelDto.Genre>?,
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
    val voteCount: Long?,
    @SerializedName("created_by")
    val createdBy: List<Any?>?,
    @SerializedName("episode_run_time")
    val episodeRunTime: List<Int>?,
    @SerializedName("first_air_date")
    val firstAirDate: String?,
    val homepage: String?,
    @SerializedName("in_production")
    val inProduction: Boolean?,
    val languages: List<String>?,
    @SerializedName("last_air_date")
    val lastAirDate: String?,
    @SerializedName("last_episode_to_air")
    val lastEpisodeToAir: LastEpisodeToAir?,
    val name: String?,
    val networks: List<Network>?,
    @SerializedName("next_episode_to_air")
    val nextEpisodeToAir: Any?,
    @SerializedName("number_of_episodes")
    val numberOfEpisodes: Int?,
    @SerializedName("number_of_seasons")
    val numberOfSeasons: Int?,
    @SerializedName("origin_country")
    val originCountry: List<String>?,
    @SerializedName("original_name")
    val originalName: String?,
    val seasons: List<Season>?,
    val type: String?,
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

    data class LastEpisodeToAir(
        @SerializedName("air_date")
        val airDate: String?,
        @SerializedName("episode_number")
        val episodeNumber: Int?,
        @SerializedName("episode_type")
        val episodeType: String?,
        val id: Int?,
        val name: String?,
        val overview: String?,
        @SerializedName("production_code")
        val productionCode: String?,
        val runtime: Int?,
        @SerializedName("season_number")
        val seasonNumber: Int?,
        @SerializedName("show_id")
        val showId: Int?,
        @SerializedName("still_path")
        val stillPath: Any?,
        @SerializedName("vote_average")
        val voteAverage: Int?,
        @SerializedName("vote_count")
        val voteCount: Int?
    )

    data class Network(
        val id: Int?,
        @SerializedName("logo_path")
        val logoPath: String?,
        val name: String?,
        @SerializedName("origin_country")
        val originCountry: String?
    )

    data class Season(
        @SerializedName("air_date")
        val airDate: String?,
        @SerializedName("episode_count")
        val episodeCount: Int?,
        val id: Int?,
        val name: String?,
        val overview: String?,
        @SerializedName("poster_path")
        val posterPath: Any?,
        @SerializedName("season_number")
        val seasonNumber: Int?,
        @SerializedName("vote_average")
        val voteAverage: Int?
    )
}


