package com.example.tmdbclone.domain

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.tmdbclone.common.Constants.AUTH_TOKEN
import com.example.tmdbclone.common.Constants.CURRENT_USERNAME
import com.example.tmdbclone.common.Constants.IS_FIRST_TIME
import com.example.tmdbclone.extension.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SessionManager @Inject constructor(private val context: Context) {

    private val authTokenKey = stringPreferencesKey(AUTH_TOKEN)
    private val usernameKey = stringPreferencesKey(CURRENT_USERNAME)
    private val firstTimeKey = booleanPreferencesKey(IS_FIRST_TIME)

    private val _isAuthorized = MutableStateFlow(false)
    val isAuthorized = _isAuthorized.asStateFlow()

    private val _currentUser = MutableStateFlow("")
    val currentUser = _currentUser.asStateFlow()

    val isFirstTime: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[firstTimeKey] ?: true
    }

    suspend fun authorize() {
        getToken().collect { token ->
            token?.let {
                _isAuthorized.value = token.isNotEmpty()

                getCurrentUserName().collect { username ->
                    _currentUser.value = username ?: "Username"
                }
            }

            _isAuthorized.value = false
        }


    }

    suspend fun saveFirstTimeFlag(isFirstTime: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[firstTimeKey] = isFirstTime
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
        it[usernameKey]
    }
}

