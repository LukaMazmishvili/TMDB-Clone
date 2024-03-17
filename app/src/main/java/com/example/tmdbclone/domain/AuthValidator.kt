package com.example.tmdbclone.domain

class AuthValidator {

    private val passwordRegex = Regex("^(?=.*[A-Z])(?=.*\\d).{8,}$")

    fun isValid(username: String, password: String): Boolean {
        if (username.isNotEmpty() && password.isNotEmpty()) {
            if (username.length >= 2 && password.length >= 8) {
                if (passwordRegex.matches(password)) {
                    return true
                }
            }
        }
        return false
    }

    fun isValid(username: String, email: String, password: String): Boolean {
        if (isValid(username, password)) {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
        return false
    }

}