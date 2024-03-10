package com.example.tmdbclone.data.remote

import android.util.Log
import com.example.tmdbclone.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

inline fun <reified T> fetchFlow(
    crossinline block: suspend () -> Response<T>
): Flow<Resource<T>> = flow {
    try {
        emit(Resource.Loading(true))
        val response = block()
        if (response.isSuccessful) {
            response.body()?.let {
                emit(Resource.Success(it))
            }
        } else {
            emit(Resource.Error("Something Went Wrong !"))
        }
    } catch (e: Exception) {
        emit(Resource.Error(e.message.toString()))
        Log.d("RequestBodyVideosExec", "fetchMovieDetails: ${e.message}")
    }
}