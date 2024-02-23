package com.example.tmdbclone.data.remote.repository

import com.example.tmdbclone.data.remote.service.AuthService
import com.example.tmdbclone.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authService: AuthService) :
    AuthRepository {

    override fun logIn(userName: String, password: String) {
        // TODO implement user login ( waiting for api )
    }
}