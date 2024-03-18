package com.example.tmdbclone.domain.model

data class SearchModel(
    val page: Int?,
    val results: List<MovieModel.Movie>
)