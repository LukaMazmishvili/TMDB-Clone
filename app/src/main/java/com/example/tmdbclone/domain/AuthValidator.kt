package com.example.tmdbclone.domain

import com.example.tmdbclone.R
import com.example.tmdbclone.common.ValidationResult

class AuthValidator {

    private val passwordRegex = Regex("^(?=.*[A-Z])(?=.*\\d).{8,}$")

    fun isValid(username: String, password: String): ValidationResult {
        if (username.isNotEmpty() && password.isNotEmpty()) {
            return if (username.length >= 2 || password.length >= 8) {
                if (passwordRegex.matches(password)) {
                    ValidationResult(success = true)
                } else {
                    ValidationResult(error = R.string.invalid_password)
                }
            } else {
                ValidationResult(error = R.string.invalid_password_or_username)
            }
        }

        return ValidationResult(false, R.string.something_went_wrong)
    }

    fun isValid(username: String, email: String, password: String): ValidationResult {
        val isValid = isValid(username, password)
        return if (isValid.success) {
            if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                ValidationResult(success = true)
            } else {
                ValidationResult(success = false, error = R.string.invalid_email)
            }
        } else {
            isValid
        }
    }

}