package com.example.tmdbclone.data.remote.service

import com.example.tmdbclone.data.remote.model.LogInDTO
import retrofit2.Response
import retrofit2.http.POST

interface AuthService {

    @POST
    suspend fun logInUser(
        userName: String,
        password: String
    ): Response<LogInDTO>

    @POST
    suspend fun registerUser(
        userName: String,
        password: String,
        email: String
    ): Response<LogInDTO> // TODO model for registering user
}