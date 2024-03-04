package com.example.tmdbclone.data.remote.service

import com.example.tmdbclone.common.Endpoints
import com.example.tmdbclone.common.Endpoints.LOGIN
import com.example.tmdbclone.common.Endpoints.REGISTER
import com.example.tmdbclone.data.remote.model.LogInDTO
import com.example.tmdbclone.data.remote.model.UserModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthService {

    @POST(LOGIN)
    @Headers("Accept: application/json")
//    @Headers("Content-Type: application/json")
    suspend fun logInUser(
        @Body userLoginModel: UserModel.UserLoginModel
    ): Response<String>

    @Headers("Accept: application/json")
    @POST(REGISTER)
    suspend fun registerUser(
        @Body userMode: UserModel.Register
    ): Response<String> // TODO model for registering user
}