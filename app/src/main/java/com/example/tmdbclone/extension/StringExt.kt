package com.example.tmdbclone.extension

import com.example.tmdbclone.common.Headings
import com.example.tmdbclone.common.MediaTypes

fun String.toHeading(): Headings {
    return when (this) {
        "Popular" -> Headings.Popular
        "Playing In Theater" -> Headings.PlayingInTheater
        "Trending" -> Headings.Trending
        "Top Rated" -> Headings.TopRated
        "Upcoming" -> Headings.Upcoming
        "Tv-Popular" -> Headings.TvPopular
        "Tv-Top Rated" -> Headings.TvTopRated
        "Tv-Trending" -> Headings.TvTrending
        "Tv-Airing Today" -> Headings.TvAiringToday
        else -> Headings.ELSE
    }
}

fun String.toMediaTypes(): MediaTypes {
    return when (this) {
        "Movie" -> MediaTypes.Movie
        "Tv Show" -> MediaTypes.TvShow
        else -> MediaTypes.None
    }
}