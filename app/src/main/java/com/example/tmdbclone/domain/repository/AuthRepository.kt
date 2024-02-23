package com.example.tmdbclone.domain.repository

interface AuthRepository {

    fun logIn(userName: String, password: String)

}