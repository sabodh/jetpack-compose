package com.virgin.jetpack_compose.viewmodel.use_case

data class ValidationResult(
    val isSuccess: Boolean = false,
    val errorMessage :String? = null
)
