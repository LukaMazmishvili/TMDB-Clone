package com.example.tmdbclone.data.remote.model

data class GenresModelDto(
    val genres: List<Genre>
) {
    data class Genre(
        val id: Int?,
        val name: String?
    )
}