package com.example.tmdbclone.data.remote

import android.util.Log
import com.example.tmdbclone.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response

inline fun <reified T, reified R> fetchFlow(
    crossinline block: suspend () -> Response<T>,
    crossinline mapper: (T) -> R
): Flow<Resource<R>> = flow {
    try {
        emit(Resource.Loading(true))
        val response = block()
        Log.d("CheckIfIsSuccessful", "fetchFlow: ${response.isSuccessful}")
        if (response.isSuccessful) {
            response.body()?.let { data ->
                val mappedData = mapper(data)
                emit(Resource.Success(mappedData))
            }
        } else {
            Log.d("CheckIfIsNotSuccessful", "fetchFlow: ${response.errorBody()}")
            emit(Resource.Error("Something Went Wrong !"))
        }
    } catch (e: HttpException) {
        Log.d("CheckHttpException", "fetchFlow: ${e.message()}")
        emit(Resource.Error(e.message(), e.code()))
    } catch (e: Exception) {
        Log.d("CheckException", "fetchFlow: ${e.message.toString()}")
        emit(Resource.Error(e.message.toString()))
    }
}