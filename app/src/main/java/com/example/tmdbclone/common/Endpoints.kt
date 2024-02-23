package com.example.tmdbclone.common

object Endpoints {

    const val BASE_URL = "https://api.themoviedb.org/3/"

    const val POPULAR_ENDPOINT =
        "discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc"

    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
}