package com.example.tmdbclone.domain.usecase

import com.example.tmdbclone.common.ValidationResult
import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.FavouriteModelDTO
import com.example.tmdbclone.data.remote.model.FavouriteResponseDto
import com.example.tmdbclone.domain.AuthValidator
import com.example.tmdbclone.domain.SessionManager
import com.example.tmdbclone.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val movieDetailsUseCase: GetMovieDetailsUseCase,
    private val sessionManager: SessionManager
) {

    suspend fun execute(username: String, password: String): Flow<Resource<String>> = flow {
        val authToken = userRepository.logIn(username, password)
        authToken.collect { response ->
            if (response is Resource.Success) {
                sessionManager.saveToken(response.data!!)
            }
            emit(response)
        }
    }

    suspend fun execute(username: String, email: String, password: String): Flow<Resource<String>> =
        flow {
            val authToken = userRepository.register(username, email, password)
            authToken.collect { response ->
                if (response is Resource.Success) {
                    sessionManager.saveToken(response.data!!)
                }
                emit(response)
            }
        }

    suspend fun addFavourite(movieId: Int): Flow<Resource<FavouriteModelDTO>> = flow {
        sessionManager.getToken().collect { token ->
            token?.let {
                userRepository.addToFavourite(
                    movieId,
                    "Bearer $token"
                ).collect { response ->
                    emit(response)
                }
            }
        }
    }

    suspend fun isFavourite(movieId: Int): Flow<Resource<FavouriteResponseDto>> = flow {
        sessionManager.getToken().collect { token ->
            token?.let {
                movieDetailsUseCase.isFavourite(movieId, "bearer $token").collect { response ->
                    emit(response)
                }
            }

        }
    }

    fun getCurrentUser() = sessionManager.currentUser

    suspend fun logOut() = sessionManager.logout()

    fun isAuthorized() = sessionManager.isAuthorized

}

