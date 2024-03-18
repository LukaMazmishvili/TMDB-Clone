package com.example.tmdbclone.data.remote.mapper

import com.example.tmdbclone.data.remote.model.SearchModelDto
import com.example.tmdbclone.domain.model.SearchModel

fun SearchModelDto.toSearchModel(genresList: Map<Int, String>): SearchModel {
    return SearchModel(
        page = this.page,
        results = mapMovie(genresList, this.results)
    )
}