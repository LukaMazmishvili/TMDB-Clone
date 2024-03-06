package com.example.tmdbclone.data.remote.service

import com.example.tmdbclone.common.Endpoints.ADD_FAVOURITE
import com.example.tmdbclone.common.Endpoints.CURRENT_USER
import com.example.tmdbclone.common.Endpoints.LOGIN
import com.example.tmdbclone.common.Endpoints.REGISTER
import com.example.tmdbclone.data.remote.model.UserModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserService {

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

    @POST(ADD_FAVOURITE)
    suspend fun addToFavourites(
        @Body movieId: Int,
        @Header("Authorization") userToken: String,
        @Header("accept") format: String = "application/json"
    ): Response<Int>

    @GET(CURRENT_USER)
    suspend fun getCurrentUser(
        @Header("Authorization") userToken: String,
        @Header("accept") format: String = "application/json"
    ): Response<UserModel>

}