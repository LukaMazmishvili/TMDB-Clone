package com.example.tmdbclone.data.remote.service

import com.example.tmdbclone.common.Endpoints.ADD_FAVOURITE
import com.example.tmdbclone.common.Endpoints.CURRENT_USER
import com.example.tmdbclone.common.Endpoints.LOGIN
import com.example.tmdbclone.common.Endpoints.REGISTER
import com.example.tmdbclone.common.Endpoints.REMOVE_FAVOURITE
import com.example.tmdbclone.data.remote.model.FavouriteModelDTO
import com.example.tmdbclone.data.remote.model.UserModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {

    @POST(LOGIN)
    @Headers("Accept: application/json")
    suspend fun logInUser(
        @Body userLoginModel: UserModel.UserLoginModel
    ): Response<String>

    @Headers("Accept: application/json")
    @POST(REGISTER)
    suspend fun registerUser(
        @Body userMode: UserModel.Register
    ): Response<UserModel>

    @POST(ADD_FAVOURITE)
    suspend fun addToFavourites(
        @Body movieId: FavouriteModelDTO,
        @Header("Authorization") userToken: String,
        @Header("accept") format: String = "application/json"
    ): Response<FavouriteModelDTO>

    @DELETE(REMOVE_FAVOURITE)
    suspend fun removeFavourite(
        @Path("movieId") movieId: Int,
        @Header("Authorization") userToken: String,
        @Header("accept") format: String = "application/json"
    ): Response<FavouriteModelDTO>

    @GET(CURRENT_USER)
    suspend fun getCurrentUser(
        @Header("Authorization") userToken: String,
        @Header("accept") format: String = "application/json"
    ): Response<UserModel>

}