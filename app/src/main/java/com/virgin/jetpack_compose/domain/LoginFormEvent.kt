package com.virgin.jetpack_compose.domain

sealed class LoginFormEvent {
    data class UsernameChanged(val username: String): LoginFormEvent()
    data class PasswordChanged(val password: String): LoginFormEvent()
    data class PasswordVisibilityChanged(val passwordVisibility: Boolean): LoginFormEvent()

    object Submit: LoginFormEvent()
}