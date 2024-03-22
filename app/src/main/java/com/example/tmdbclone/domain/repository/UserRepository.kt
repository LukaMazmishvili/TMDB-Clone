package com.example.tmdbclone.domain.repository

import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.FavouriteModelDTO
import com.example.tmdbclone.data.remote.model.UserModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun logIn(userName: String, password: String): Flow<Resource<String>>
    suspend fun register(userName: String, email: String, password: String): Flow<Resource<UserModel>>
    suspend fun addToFavourite(movieId: Int, userToken: String): Flow<Resource<FavouriteModelDTO>>

    suspend fun removeFavourite(movieId: Int, userToken: String): Flow<Resource<Unit>>
    suspend fun getCurrentUser(userToken: String): Flow<Resource<UserModel>>

    suspend fun saveUserToken(token: String)

}