package com.virgin.jetpack_compose.domain

data class LoginFormState(
    val userName : String = "",
    val password : String = "",
    val userNameError : String? = null,
    val passwordError : String?= null,
    var passwordVisibility: Boolean = false
)
