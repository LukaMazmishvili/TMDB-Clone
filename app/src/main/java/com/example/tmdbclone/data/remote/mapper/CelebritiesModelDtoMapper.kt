package com.example.tmdbclone.data.remote.mapper

import com.example.tmdbclone.data.remote.model.CelebritiesModelDto
import com.example.tmdbclone.data.remote.model.MoviesDTO
import com.example.tmdbclone.domain.model.CelebritiesModel
import com.example.tmdbclone.domain.model.MovieModel
import com.example.tmdbclone.extension.toGender

fun CelebritiesModelDto.toCelebritiesModel(): CelebritiesModel {
    return CelebritiesModel(
        id = this.id ?: 0,
        page = this.page ?: 0,
        results = mapResult(this.results),
        cast = mapResult(this.cast)
    )
}

fun mapResult(results: List<CelebritiesModelDto.Result>?): List<CelebritiesModel.Result> {
    return results?.map {
        CelebritiesModel.Result(
            it.adult,
            it.id,
            it.name,
            it.originalName,
            it.mediaType,
            it.popularity,
            it.gender?.toGender(),
            it.castId,
            it.character,
            it.creditId,
            it.order,
            it.knownForDepartment,
            it.profilePath,
            it.knownFor
        )
    } ?: emptyList()
}