package com.example.tmdbclone.data.remote

class AuthValidator {

    inner class LoginValidator() {
        fun isValid(username: String, password: String) {
            if (username.isEmpty() && password.isEmpty()) {

            }
        }
    }

}