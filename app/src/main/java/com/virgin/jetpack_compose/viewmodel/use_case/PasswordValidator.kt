package com.virgin.jetpack_compose.viewmodel.use_case

class PasswordValidator {

    fun execute(password: String): ValidationResult{
        if(password.isBlank()){
            return ValidationResult(
                isSuccess = false,
                errorMessage = "Password can't be empty")

        }
        if(password.length < 3){
            return ValidationResult(
                isSuccess = false,
                errorMessage = "Password must contain 4 characters."
            )
        }
        // success case
        return ValidationResult(
            isSuccess = true
        )

    }
}