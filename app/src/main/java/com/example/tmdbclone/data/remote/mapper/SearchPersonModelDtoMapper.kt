package com.example.tmdbclone.data.remote.mapper

import com.example.tmdbclone.data.remote.model.SearchPersonModelDto
import com.example.tmdbclone.domain.model.SearchPersonModel

fun SearchPersonModelDto.toSearchPersonModel() : SearchPersonModel {
    return SearchPersonModel(
        results = mapResult(this.results)
    )
}