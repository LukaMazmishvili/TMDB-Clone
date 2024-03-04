package com.example.tmdbclone.domain.usecase

import com.example.tmdbclone.data.remote.AuthValidator
import com.example.tmdbclone.domain.repository.AuthRepository
import javax.inject.Inject


class AuthUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val validator: AuthValidator
) {

    suspend fun execute(username: String, password: String) {
        if (validator.isValid(username, password))
            authRepository.logIn(username, password)
    }

    suspend fun execute(username: String, email: String, password: String) {
        if (validator.isValid(username, email, password)) {
            authRepository.register(username, email, password)
        }else {
            println(" ravi bachu")
        }
    }

}