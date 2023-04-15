package com.virgin.jetpack_compose.use_case

class UserNameValidator {

    fun execute(username: String): ValidationResult {
        if(username.isBlank()){
            return ValidationResult(
                isSuccess = false,
                errorMessage = "Username can't be empty")

        }
        // success case
        return ValidationResult(
            isSuccess = true
        )

    }
}