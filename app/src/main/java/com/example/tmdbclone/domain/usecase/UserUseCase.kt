package com.example.tmdbclone.domain.usecase

import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.domain.AuthValidator
import com.example.tmdbclone.domain.SessionManager
import com.example.tmdbclone.domain.repository.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class UserUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val validator: AuthValidator,
    private val sessionManager: SessionManager
) {

    suspend fun execute(username: String, password: String): Flow<Resource<String>> = flow {
        if (validator.isValid(username, password)) {

            val authToken = userRepository.logIn(username, password)

            authToken.collect { response ->
                if (response is Resource.Success) {
                    sessionManager.saveToken(response.data!!)
                }
                emit(response)
            }
        }
    }

    suspend fun execute(username: String, email: String, password: String) {
        if (validator.isValid(username, email, password)) {

            val authToken = userRepository.register(username, email, password)

            if (authToken.isNotEmpty()) {
                sessionManager.saveToken(authToken)
            }
        }
    }

    suspend fun addFavourite(movieId: Int) {
//        if (isAuthorized()) {
        sessionManager.getToken().collect { token ->
            token?.let {
                userRepository.addToFavourite(
                    movieId,
                    token
                )
            }

        }
//        }
    }

    fun getCurrentUser() = sessionManager.currentUser

    suspend fun logOut() = sessionManager.logout()

    fun isAuthorized() = sessionManager.isAuthorized

}

