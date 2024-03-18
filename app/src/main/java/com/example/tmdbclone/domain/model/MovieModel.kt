package com.example.tmdbclone.domain.model

import com.google.gson.annotations.SerializedName

data class MovieModel(
    val dates: Dates?,
    val page: Int?,
    val results: List<Movie>?
) {

    data class Dates(
        val maximum: String?,
        val minimum: String?
    )

    data class Movie(
        val adult: Boolean?,
        val backdropPath: String?,
        val genreIds: List<String>?,
        val id: Int?,
        val originalLanguage: String?,
        val originalTitle: String?,
        val originalName: String?,
        val overview: String?,
        val popularity: Double?,
        val posterPath: String?,
        val mediaType: String?,
        val releaseDate: String?,
        val title: String?,
        val video: Boolean?,
        val voteAverage: Float?,
        val voteCount: Long?
    )
}