package com.example.tmdbclone.data.remote.model

import com.google.gson.annotations.SerializedName


// This model is combination of Movies and Tv Shows models
data class MoviesDTO(
    val dates: Dates,
    val page: Int?,
    val results: List<MovieModelDto>
) {

    data class Dates(
        val maximum: String,
        val minimum: String
    )

    data class MovieModelDto(
        val adult: Boolean?,
        @SerializedName("backdrop_path")
        val backdropPath: String?,
        @SerializedName("genre_ids")
        val genreIds: List<Int?>?,
        val id: Int?,
        @SerializedName("original_language")
        val originalLanguage: String?,
        @SerializedName("original_title")
        val originalTitle: String?,
        @SerializedName("original_name")
        val originalName: String?,
        val overview: String?,
        val popularity: Double?,
        @SerializedName("poster_path")
        val posterPath: String?,
        @SerializedName("media_type")
        val mediaType: String,
        @SerializedName("release_date")
        val releaseDate: String?,
        val title: String?,
        val video: Boolean?,
        @SerializedName("vote_average")
        val voteAverage: Float?,
        @SerializedName("vote_count")
        val voteCount: Long?
    )
}