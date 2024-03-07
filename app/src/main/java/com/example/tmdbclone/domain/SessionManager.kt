package com.example.tmdbclone.domain

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.tmdbclone.common.Constants.AUTH_TOKEN
import com.example.tmdbclone.common.Constants.CURRENT_USERNAME
import com.example.tmdbclone.data.remote.model.UserModel
import com.example.tmdbclone.extension.dataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SessionManager @Inject constructor(private val context: Context) {

    private val authTokenKey = stringPreferencesKey(AUTH_TOKEN)
    private val usernameKey = stringPreferencesKey(CURRENT_USERNAME)

    private val _isAuthorized = MutableStateFlow(false)
    val isAuthorized = _isAuthorized.asStateFlow()

    private val _currentUser = MutableStateFlow("")
    val currentUser = _currentUser.asStateFlow()

    suspend fun authorize() {
        getToken().collect { token ->
            token?.let {
                _isAuthorized.value = token.isNotEmpty()

                getCurrentUserName().collect { username ->
                    isAuthorized.collect {
                        if (it)
                            username?.let {
                                _currentUser.value = username
                            }
                    }
                }
            }
        }
    }

    suspend fun saveToken(authToken: String) {
        context.dataStore.edit {
            it[authTokenKey] = authToken
        }
    }

    fun getToken(): Flow<String?> = context.dataStore.data.map {
        it[authTokenKey]
    }

    suspend fun logout() {
        context.dataStore.edit {
            it[authTokenKey] = ""
            it[usernameKey] = ""
        }
    }

    suspend fun saveUsername(username: String) {
        context.dataStore.edit {
            it[usernameKey] = username
        }
    }

    private fun getCurrentUserName(): Flow<String?> = context.dataStore.data.map {
//        it[usernameKey]?.let { username ->
//            _currentUser.value = username
//        }
        it[usernameKey]
    }
}

