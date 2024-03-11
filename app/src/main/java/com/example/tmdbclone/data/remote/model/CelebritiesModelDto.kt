package com.example.tmdbclone.data.remote.model

import com.google.gson.annotations.SerializedName

data class CelebritiesModelDto(
    val id: Int?,
    val page: Int?,
    val results: List<Result>?,
    val cast: List<Result>?

) {

    data class Result(
        val adult: Boolean?,
        val id: Int?,
        val name: String?,
        @SerializedName("original_name")
        val originalName: String?,
        @SerializedName("media_type")
        val mediaType: String?,
        val popularity: Float?,
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
        val knownFor: List<PopularMovieDTO>?, // todo change model into something global or separate models
    )

}