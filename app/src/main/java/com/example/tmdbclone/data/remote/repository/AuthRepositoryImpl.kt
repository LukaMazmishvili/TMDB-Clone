package com.example.tmdbclone.data.remote.repository

import com.example.tmdbclone.data.remote.service.AuthService
import com.example.tmdbclone.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authService: AuthService) :
    AuthRepository {

    override suspend fun logIn(userName: String, password: String) {

    }

    override suspend fun register(userName: String, password: String, email: String) {


    }
}