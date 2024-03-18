package com.example.tmdbclone.data.remote.mapper

import com.example.tmdbclone.data.remote.model.CelebrityDetailsModelDto
import com.example.tmdbclone.domain.model.CelebrityDetailsModel


fun CelebrityDetailsModelDto.toCelebrityDetailsModel(): CelebrityDetailsModel {
    return CelebrityDetailsModel(
        adult,
        alsoKnownAs,
        biography,
        birthday,
        deathDay,
        gender,
        homepage,
        id,
        imdbId,
        knownForDepartment,
        name,
        placeOfBirth,
        popularity,
        profilePath
    )
}