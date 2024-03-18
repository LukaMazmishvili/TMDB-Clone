package com.example.tmdbclone.extension

fun Map<Int, String>.toGenre(list: List<Int>?): List<String> {
    val genres = mutableListOf<String>()
    list?.forEach { genreId ->
        this.keys.forEach {
            if (genreId == it) {
                this[genreId]?.let { genre ->
                    genres.add(genre)
                }
            }
        }
    }
    return genres
}