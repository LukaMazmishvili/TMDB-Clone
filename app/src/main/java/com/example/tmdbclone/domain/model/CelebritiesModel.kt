package com.example.tmdbclone.domain.model

import com.example.tmdbclone.data.remote.model.MoviesDTO

data class CelebritiesModel(
    val id: Int?,
    val page: Int?,
    val results: List<Result>?,
    val cast: List<Result>?
) {

    data class Result(
        val adult: Boolean?,
        val id: Int?,
        val name: String?,
        val originalName: String?,
        val mediaType: String?,
        val popularity: Float?,
        val gender: String?,
        val castId: Int?,
        val character: String?,
        val creditId: String?,
        val order: Int?,
        val knownForDepartment: String?,
        val profilePath: String?,
        val knownFor: List<MoviesDTO>?
    )

}
