package com.example.tmdbclone.domain.usecase

import com.example.tmdbclone.common.ValidationResult
import com.example.tmdbclone.domain.AuthValidator
import javax.inject.Inject

class ValidationUseCase @Inject constructor(
    private val validator: AuthValidator
) {

    fun validate(username: String, password: String): ValidationResult {
        val validator = validator.isValid(username, password)
        return if (validator.success) {
            validator
        } else {
            validator
        }
    }

    fun validate(username: String, email: String, password: String): ValidationResult {
        val validator = validator.isValid(username, email, password)
        return if (validator.success) {
            validator
        } else {
            validator
        }
    }

}