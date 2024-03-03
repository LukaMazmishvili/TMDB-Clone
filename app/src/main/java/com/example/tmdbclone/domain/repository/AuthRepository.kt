package com.example.tmdbclone.domain.repository

interface AuthRepository {

    suspend fun logIn(userName: String, password: String)
    suspend fun register(userName: String, email: String, password: String)

}