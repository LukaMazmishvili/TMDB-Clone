package com.example.tmdbclone.domain.model

import com.example.tmdbclone.data.remote.model.CelebritiesModelDto

data class SearchPersonModel(
    val results: List<CelebritiesModel.Result>
)