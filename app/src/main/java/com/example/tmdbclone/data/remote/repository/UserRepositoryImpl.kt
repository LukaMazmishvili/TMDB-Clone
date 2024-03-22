package com.example.tmdbclone.data.remote.repository

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.tmdbclone.common.Constants.AUTH_TOKEN
import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.FavouriteModelDTO
import com.example.tmdbclone.data.remote.model.UserModel
import com.example.tmdbclone.data.remote.service.UserService
import com.example.tmdbclone.domain.SessionManager
import com.example.tmdbclone.domain.repository.UserRepository
import com.example.tmdbclone.extension.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService,
    private val sessionManager: SessionManager,
) : UserRepository {

    override suspend fun logIn(userName: String, password: String): Flow<Resource<String>> = flow {
        try {

            val response = userService.logInUser(UserModel.UserLoginModel(userName, password))

            if (response.isSuccessful) {
                response.body()?.let { token ->
                    saveUserToken(token)
                    getCurrentUser(token)
                }

                emit(Resource.Success(response.body()))

            } else {
                emit(Resource.Error(response.errorBody()!!.string(), response.code()))
            }

        } catch (e: HttpException) {
            emit(Resource.Error(e.message().toString(), e.code()))

        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

    override suspend fun register(
        userName: String,
        email: String,
        password: String
    ): Flow<Resource<String>> = flow {
        try {
            val response = userService.registerUser(UserModel.Register(userName, password, email))

            if (response.isSuccessful) {
                emit(Resource.Success(response.body()))

            } else {
                emit(Resource.Error(response.errorBody()!!.string(), response.code()))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.message().toString(), e.code()))

        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))

        }
    }

    override suspend fun addToFavourite(
        movieId: Int,
        userToken: String
    ): Flow<Resource<FavouriteModelDTO>> =
        flow {
            try {
                val response = userService.addToFavourites(FavouriteModelDTO(movieId), userToken)

                if (response.isSuccessful) {
                    val body = response.body()
                    emit(Resource.Success(body))
                } else {

                    emit(Resource.Error(response.errorBody()!!.string(), response.code()))
                }

            } catch (e: HttpException) {
                emit(Resource.Error(e.message().toString(), e.code()))

            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }

    override suspend fun getCurrentUser(userToken: String): Flow<Resource<UserModel>> = flow {
        try {
            val response = userService.getCurrentUser("Bearer $userToken")

            if (response.isSuccessful) {
                val body = response.body()
                body?.username?.let { username ->
                    sessionManager.saveUsername(username)
                }

                emit(Resource.Success(body))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.message().toString(), e.code()))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

    override suspend fun saveUserToken(token: String) {
        sessionManager.saveToken(token)
    }

}