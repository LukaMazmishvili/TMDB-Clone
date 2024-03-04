package com.example.tmdbclone.domain.repository

import com.example.tmdbclone.data.remote.model.LogInDTO

interface AuthRepository {

    suspend fun logIn(userName: String, password: String): String
    suspend fun register(userName: String, email: String, password: String): String

}