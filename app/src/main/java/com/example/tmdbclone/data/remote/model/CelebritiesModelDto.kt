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


/*
* "adult" : false,
    "backdrop_path": "/8XyX5Us7uzwQdXtAdnhCB9Gquek.jpg",
"id": 297761,
"title": "Suicide Squad",
"original_language": "en",
"original_title": "Suicide Squad",
"overview": "From DC Comics comes the Suicide Squad, an antihero team of incarcerated supervillains who act as deniable assets for the United States government, undertaking high-risk black ops missions in exchange for commuted prison sentences.",
"poster_path": "/xFw9RXKZDvevAGocgBK0zteto4U.jpg",
"media_type": "movie",
"genre_ids": [
28,
12,
14
],
"popularity": 55.062,
"release_date": "2016-08-03",
"video": false,
"vote_average": 5.911,
"vote_count": 20478
* */
