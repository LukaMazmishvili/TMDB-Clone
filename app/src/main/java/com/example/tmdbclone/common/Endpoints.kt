package com.example.tmdbclone.common

object Endpoints {

    // Base Endpoints
    const val BEARER_TOKEN =
        "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmMDFhZTg3ZjMyZmFlYzRkNzgzNzFiYjkzNDc1MTNiZiIsInN1YiI6IjY1ZDgzZmRhMTQ5NTY1MDE3YmY1YjYwNiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.QSLyzbWcFHJnHlYUQOVyJy-i4EW_Z7EEy8PFS-fJ2io"
    const val BASE_URL = "http://api.themoviedb.org/3/"
    const val IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w500"
    const val OUR_API_BASE_URL = "http://10.15.10.85:5000/api/"

    // Movie Endpoints
    const val POPULAR_ENDPOINT =
        "discover/movie?include_adult=false&include_video=false&language=en-US &sort_by=popularity.desc"
    const val NOW_PLAYING = "movie/now_playing?language=en-US"
    const val TRENDING_ALL = "trending/all/day?language=en-US"
    const val TOP_RATED = "movie/top_rated?language=en-US"
    const val UPCOMING = "movie/upcoming?language=en-US"

    // Tv Shows Endpoints
    const val AIRING_TODAY = "tv/airing_today?language=en-US&page=1"
    const val TRENDING_TV_SHOWS = "trending/tv/day?language=en-US"
    const val TOP_RATED_TV_SHOWS = "tv/top_rated?language=en-US&page=1"
    const val POPULAR_TV_SHOWS = "tv/popular?language=en-US&page=1"

    // Celebrities Endpoints
    const val POPULAR_CELEBRITIES = "person/popular?language=en-US&page=1"
    const val TRENDING_CELEBRITIES = "trending/person/day?language=en-US"

    // Genres Endpoints
    const val MOVIE_GENRES = "genre/movie/list?language=en"
    const val TV_SHOW_GENRES = "genre/movie/list?language=en"

    // Search Endpoints
    const val SEARCH_MOVIES = "search/movie?&include_adult=false&language=en-US"
    const val SEARCH_TV_SHOWS = "search/tv?&include_adult=false&language=en-US"
    const val SEARCH_PERSON = "search/person?&include_adult=false&language=en-US"
    const val SEARCH_MULTI = "search/multi?&include_adult=false&language=en-US"

    // Movie Detail Endpoints
    const val MOVIE_DETAILS = "movie/{movieId}?language=en-US"
    const val MOVIE_CAST = "movie/{movieId}/credits?language=en-US"
    const val MOVIE_VIDEOS = "movie/{movieId}/videos?language=en-US"
    const val MOVIE_RECOMMENDATION = "movie/{movieId}/recommendations?language=en-US&page=1"
    const val MOVIE_SIMILAR = "movie/{movieId}/similar?language=en-US&page=1"

    // User Endpoints
    const val LOGIN = "Users/Login"
    const val REGISTER = "Users/Register"
    const val ADD_FAVOURITE = "Favourites/AddFavourite"
    const val CURRENT_USER = "Users/CurrentUser"

}