package com.example.tmdbclone.data.remote.model

import com.google.gson.annotations.SerializedName

data class SearchSimilarModelDto(
    val page: Int?,
    val results: List<SimilarSearches>
) {
    data class SimilarSearches(
        val adult: Boolean?,
        @SerializedName("backdrop_path")
        val backdropPath: String?,
        @SerializedName("genre_ids")
        val genreIds: List<Int>?,
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
        val voteCount: Long?,
        val name: String?,
        val gender: Int?,
        @SerializedName("cast_id")
        val castId: Int?,
        val character: String?,
        @SerializedName("credit_id")
        val creditId: String?,
        val order: Int?,
        @SerializedName("known_for_department")
        val knownForDepartment: String?,
        @SerializedName("profile_path")
        val profilePath: String?,
        @SerializedName("known_for")
        val knownFor: List<MoviesDTO>?
    )
}
