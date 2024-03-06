package com.example.tmdbclone.data.remote.repository

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.tmdbclone.common.Constants.AUTH_TOKEN
import com.example.tmdbclone.data.remote.model.UserModel
import com.example.tmdbclone.data.remote.service.UserService
import com.example.tmdbclone.domain.SessionManager
import com.example.tmdbclone.domain.repository.UserRepository
import com.example.tmdbclone.extension.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService,
    private val sessionManager: SessionManager,
) : UserRepository {

    override suspend fun logIn(userName: String, password: String): String {
        try {

            val response = userService.logInUser(UserModel.UserLoginModel(userName, password))

            if (response.isSuccessful) {
                response.body()?.let { token ->
                    sessionManager.saveToken(token)
                    getCurrentUser(token)
                }

                return response.body() ?: ""

            }

        } catch (e: Exception) {
            return e.message.toString()
        }
        return ""
    }

    override suspend fun register(userName: String, email: String, password: String): String {
        try {
            val response = userService.registerUser(UserModel.Register(userName, password, email))

            if (response.isSuccessful) {
                println(response.code())
                return response.body() ?: ""

            } else {
                println(response.code())
            }
        } catch (e: Exception) {
            return e.message.toString()
        }
        return ""
    }

    override suspend fun addToFavourite(movieId: Int, userToken: String): Int {
        return try {

            val response = userService.addToFavourites(movieId, userToken)

            if (response.isSuccessful) {
                1
            } else {
                0
            }

        } catch (e: Exception) {
            Log.d("AddFavouritesExceptionMessage", "addToFavourite: ${e.message}")
            0
        }
    }

    override suspend fun getCurrentUser(userToken: String) {
        try {

            val response = userService.getCurrentUser("Bearer $userToken")

            if (response.isSuccessful) {
                response.body()!!.username?.let { username ->
                    sessionManager.saveUsername(username)
                }
            }
        } catch (e: Exception) {
            Log.d("CurrentUserExc", "getCurrentUser: ${e.message}")
        }
    }


}