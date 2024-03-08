package com.example.tmdbclone.domain.repository

import com.example.tmdbclone.data.remote.model.UserModel

interface UserRepository {

    suspend fun logIn(userName: String, password: String): String
    suspend fun register(userName: String, email: String, password: String): String
    suspend fun addToFavourite(movieId: Int, userToken: String = ""): Int
    suspend fun getCurrentUser(userToken: String)

    suspend fun saveUserToken(token: String)

}