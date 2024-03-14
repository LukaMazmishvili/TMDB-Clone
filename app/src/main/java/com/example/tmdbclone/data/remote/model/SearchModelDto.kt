package com.example.tmdbclone.data.remote.model

data class SearchModelDto(
    val page: Int?,
    val results: List<PopularMovieDTO.MovieModelDto>
)