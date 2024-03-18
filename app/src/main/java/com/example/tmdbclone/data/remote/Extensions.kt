package com.example.tmdbclone.data.remote

import android.util.Log
import com.example.tmdbclone.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

inline fun <reified T, reified R> fetchFlow(
    crossinline block: suspend () -> Response<T>,
    crossinline mapper: (T) -> R
): Flow<Resource<R>> = flow {
    try {
        emit(Resource.Loading(true))
        val response = block()
        if (response.isSuccessful) {
            response.body()?.let { data ->
                val mappedData = mapper(data)
                emit(Resource.Success(mappedData))
            }
        } else {
            emit(Resource.Error("Something Went Wrong !"))
        }
    } catch (e: Exception) {
        emit(Resource.Error(e.message.toString()))
        Log.d("RequestBodyVideosExec", "fetchMovieDetails: ${e.message}")
    }
}