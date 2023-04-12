package com.virgin.jetpack_compose.presentation

data class LoginFormState(
    val userName : String = "",
    val password : String = "",
    val userNameError : String? = null,
    val passwordError : String?= null,
    var passwordVisibility: Boolean = false
)
