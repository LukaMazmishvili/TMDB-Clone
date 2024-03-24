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

interface SessionManager {

    val isAuthorized: Flow<Boolean>
    val currentUser: Flow<String>
    val isFirstTime: Flow<Boolean>

    suspend fun authorize()
    suspend fun saveFirstTimeFlag(isFirstTime: Boolean)
    suspend fun saveToken(authToken: String)
    fun getToken(): Flow<String?>
    suspend fun logout()
    suspend fun saveUsername(username: String)
}

