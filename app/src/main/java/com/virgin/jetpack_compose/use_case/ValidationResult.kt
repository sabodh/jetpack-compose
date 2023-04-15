package com.virgin.jetpack_compose.use_case

data class ValidationResult(
    val isSuccess: Boolean = false,
    val errorMessage :String? = null
)
