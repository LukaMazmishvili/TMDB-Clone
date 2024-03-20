package com.example.tmdbclone.common

import androidx.annotation.StringRes

data class ValidationResult(
    val success: Boolean = false,
    @StringRes val error: Int? = null
)
