package com.example.tmdbclone.data.remote.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.tmdbclone.common.Constants.AUTH_TOKEN
import com.example.tmdbclone.common.Constants.USER_PREFERENCES
import com.example.tmdbclone.data.remote.model.UserModel
import com.example.tmdbclone.data.remote.service.AuthService
import com.example.tmdbclone.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.lang.Exception
import javax.inject.Inject

val Context.dataStore by preferencesDataStore(name = USER_PREFERENCES)

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
    private val context: Context
) :
    AuthRepository {

    private val authTokenKey = stringPreferencesKey(AUTH_TOKEN)

    fun getAuthToken(): Flow<String?> {
        return context.dataStore.data.map {
            it[authTokenKey]
        }
    }

    private suspend fun saveAuthToken(authToken: String) {
        context.dataStore.edit {
            it[authTokenKey] = authToken
        }
    }

    override suspend fun logIn(userName: String, password: String): String {
        try {

            val response = authService.logInUser(UserModel.UserLoginModel(userName, password))

            if (response.isSuccessful) {
                response.body()?.let {
                    saveAuthToken(it)
                }

                getAuthToken().collect {
                    println("authToken" + it)
                }
//                println(response.body())
                return response.body() ?: ""

            }


            println(response.code())
        } catch (e: Exception) {
            return e.message.toString()
        }
        return ""
    }

    override suspend fun register(userName: String, email: String, password: String): String {
        try {

            val response = authService.registerUser(UserModel.Register(userName, password, email))

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
}