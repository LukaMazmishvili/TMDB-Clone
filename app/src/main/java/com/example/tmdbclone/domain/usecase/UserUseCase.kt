package com.example.tmdbclone.domain.usecase

import com.example.tmdbclone.data.remote.AuthValidator
import com.example.tmdbclone.domain.SessionManager
import com.example.tmdbclone.domain.repository.UserRepository
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val validator: AuthValidator,
    private val sessionManager: SessionManager
) {

    suspend fun execute(username: String, password: String) {
        if (validator.isValid(username, password)) {

            val authToken = userRepository.logIn(username, password)

            if (authToken.isNotEmpty()) {
                sessionManager.saveToken(authToken)
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

