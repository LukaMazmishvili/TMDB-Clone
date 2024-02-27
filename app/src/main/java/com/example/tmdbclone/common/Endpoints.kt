package com.example.tmdbclone.common

object Endpoints {

    const val BEARER_TOKEN =
        "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmMDFhZTg3ZjMyZmFlYzRkNzgzNzFiYjkzNDc1MTNiZiIsInN1YiI6IjY1ZDgzZmRhMTQ5NTY1MDE3YmY1YjYwNiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.QSLyzbWcFHJnHlYUQOVyJy-i4EW_Z7EEy8PFS-fJ2io"
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

    // Movie Endpoints
    const val POPULAR_ENDPOINT =
        "discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc"
    const val NOW_PLAYING = "movie/now_playing?language=en-US&page=1"
    const val TRENDING_ALL = "trending/all/day?language=en-US"
    const val TOP_RATED = "movie/top_rated?language=en-US&page=1"
    const val UPCOMING = "movie/upcoming?language=en-US&page=1"

    // Tv Shows Endpoints
    const val AIRING_TODAY = "tv/airing_today?language=en-US&page=1"
    const val TRENDING_TV_SHOWS = "trending/tv/day?language=en-US"
    const val TOP_RATED_TV_SHOWS = "tv/top_rated?language=en-US&page=1"
    const val POPULAR_TV_SHOWS = "tv/popular?language=en-US&page=1"

    // Celebrities Endpoints
    const val POPULAR_CELEBRITIES = "person/popular?language=en-US&page=1"
    const val TRENDING_CELEBRITIES = "trending/person/day?language=en-US"

}