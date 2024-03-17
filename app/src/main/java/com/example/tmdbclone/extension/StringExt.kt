package com.example.tmdbclone.extension

import com.example.tmdbclone.common.Headings

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