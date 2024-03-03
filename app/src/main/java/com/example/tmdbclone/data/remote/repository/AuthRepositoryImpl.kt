package com.example.tmdbclone.data.remote.repository

import com.example.tmdbclone.data.remote.service.AuthService
import com.example.tmdbclone.domain.repository.AuthRepository
import java.lang.Exception
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authService: AuthService) :
    AuthRepository {

    override suspend fun logIn(userName: String, password: String) {
        try {

            val response = authService.logInUser(userName, password)

            if (response.isSuccessful) {

                println(response.body())

            }

        } catch (e: Exception) {
            println(e.message)
        }
    }

    override suspend fun register(userName: String, email: String, password: String) {
        try {

            val response = authService.logInUser(userName, password)

            if (response.isSuccessful) {

                println(response.body())

            }

        } catch (e: Exception) {
            println(e.message)
        }
    }
}