package com.example.tmdbclone.extension

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.example.tmdbclone.common.Constants


val Context.dataStore by preferencesDataStore(name = Constants.USER_PREFERENCES)