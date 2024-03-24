package com.example.tmdbclone.data.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.tmdbclone.common.Constants
import com.example.tmdbclone.domain.SessionManager
import com.example.tmdbclone.extension.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SessionManagerImpl @Inject constructor(private val context: Context) : SessionManager {

    private val authTokenKey = stringPreferencesKey(Constants.AUTH_TOKEN)
    private val usernameKey = stringPreferencesKey(Constants.CURRENT_USERNAME)
    private val firstTimeKey = booleanPreferencesKey(Constants.IS_FIRST_TIME)

    private val _isAuthorized = MutableStateFlow(false)
    override val isAuthorized = _isAuthorized.asStateFlow()

    private val _currentUser = MutableStateFlow("")
    override val currentUser = _currentUser.asStateFlow()

    override val isFirstTime: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[firstTimeKey] ?: true
    }

    override suspend fun authorize() {
        getToken().collect { token ->
            token?.let {
                _isAuthorized.value = token.isNotEmpty()

                getCurrentUserName().collect { username ->
                    _currentUser.value = username ?: "Username"
                }
            }
        }
    }

    override suspend fun saveFirstTimeFlag(isFirstTime: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[firstTimeKey] = isFirstTime
        }
    }

    override suspend fun saveToken(authToken: String) {
        context.dataStore.edit {
            it[authTokenKey] = authToken
        }
    }

    override fun getToken(): Flow<String?> = context.dataStore.data.map {
        it[authTokenKey]
    }

    override suspend fun logout() {
        context.dataStore.edit {
            it[authTokenKey] = ""
            it[usernameKey] = ""
        }
    }

    override suspend fun saveUsername(username: String) {
        context.dataStore.edit {
            it[usernameKey] = username
        }
    }

    private fun getCurrentUserName(): Flow<String?> = context.dataStore.data.map {
        it[usernameKey]
    }
}