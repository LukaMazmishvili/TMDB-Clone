package com.example.tmdbclone.data.remote.model

data class UserModel(
    val token: String
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

    data class UserPreferences(
        val authToken: String = ""
    )
}