package com.example.tmdbclone.data.remote.model

data class UserModel(
    val id: String?,
    val username: String?,
    val password: String?,
    val email: String?,
    val userFavoriteMovies: List<Movie>
) {
    data class UserLoginModel(
        val username: String,
        val password: String
    )

    data class Register(
        val username: String,
        val email: String,
        val password: String
    )

    data class Movie(
        val movieId: Int
    )

}