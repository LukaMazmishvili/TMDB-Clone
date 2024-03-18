package com.example.tmdbclone.domain.model

import com.example.tmdbclone.data.remote.model.MoviesDTO
import com.google.gson.annotations.SerializedName

data class SearchSimilarModel(
    val page: Int?,
    val results: List<SimilarSearches>
) {
    data class SimilarSearches(
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
        val mediaType: String,
        val releaseDate: String?,
        val title: String?,
        val video: Boolean?,
        val voteAverage: Float?,
        val voteCount: Long?,
        val name: String?,
        val gender: String?,
        val castId: Int?,
        val character: String?,
        val creditId: String?,
        val order: Int?,
        val knownForDepartment: String?,
        val profilePath: String?,
        val knownFor: List<MovieModel>?
    )
}