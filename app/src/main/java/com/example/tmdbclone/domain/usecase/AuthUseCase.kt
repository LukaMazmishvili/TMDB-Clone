package com.example.tmdbclone.domain.usecase

import com.example.tmdbclone.domain.repository.AuthRepository
import javax.inject.Inject


class AuthUseCase @Inject constructor(private val authRepository: AuthRepository) {

    suspend fun execute(username: String, password: String) =
        authRepository.logIn(username, password)

    suspend fun execute(username: String, email: String, password: String) = authRepository.register(username,email, password)

}