package com.example.tmdbclone.common

sealed class Resource<T> {
    data class Success<T>(val data: T?) : Resource<T>()
    data class Error<T>(val errorMsg: String, val statusCode: Int? = null) : Resource<T>()
    data class Loading<T>(val isLoading: Boolean) : Resource<T>()
}