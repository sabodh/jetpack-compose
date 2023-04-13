package com.virgin.jetpack_compose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virgin.jetpack_compose.model.NetworkState
import com.virgin.jetpack_compose.presentation.LoginFormEvent
import com.virgin.jetpack_compose.presentation.LoginFormState
import com.virgin.jetpack_compose.repository.UserRepository
import com.virgin.jetpack_compose.viewmodel.use_case.PasswordValidator
import com.virgin.jetpack_compose.viewmodel.use_case.UserNameValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class UserViewModel(
    private  val passwordValidator: PasswordValidator = PasswordValidator(),
    private  val userNameValidator: UserNameValidator = UserNameValidator()

) : ViewModel() {
    private val userRepository = UserRepository()
    private val _loginState = MutableStateFlow<NetworkState>(NetworkState.Initial)
    val loginState: StateFlow<NetworkState>
        get() = _loginState


    // used to identify ui states
    private var _uiState by mutableStateOf(LoginFormState())
    val uiState: LoginFormState
        get() = _uiState


    fun onEvent(event: LoginFormEvent){
        when(event){
            is LoginFormEvent.UsernameChanged->{
                _uiState = _uiState.copy(userName = event.username)
            }
            is LoginFormEvent.PasswordChanged->{
                _uiState = _uiState.copy(password = event.password)
            }
            is LoginFormEvent.PasswordVisibilityChanged->{
                _uiState = _uiState.copy(passwordVisibility = !event.passwordVisibility)
            }
            is LoginFormEvent.Submit->{
                submitData()
            }
        }
    }

    private fun submitData() {
        val password = passwordValidator.execute(_uiState.password)
        val username = userNameValidator.execute(_uiState.userName)
        val status = listOf(password,
        username).any { !it.isSuccess }
        // out side loop is to clear the error message while submit.
        _uiState = _uiState.copy(
            passwordError = password.errorMessage,
            userNameError = username.errorMessage
        )
        if(status){
            return
        }
        loadData(_uiState.userName, _uiState.password)
    }

    private fun loadData(userName: String, password: String) {
        loginUser(userName, password)
    }

    private fun loginUser(userName: String, password: String) =
        viewModelScope.launch {
            try {
                _loginState.value = NetworkState.Loading
                userRepository.validateLogin(userName, password).catch { e ->
                    _loginState.value = NetworkState.Error(e.message ?: "Unknown Error occurred")
                }.collect {
                    if(it.isSuccessful){
                        _loginState.value = NetworkState.Success(it.body())
                    }
                    else {
                        _loginState.value =
                            NetworkState.Error(it.errorBody()?.string() ?: "Unknown Error occurred")
                    }
                }
            } catch (e: Exception) {
                _loginState.value = NetworkState.Error(e.message ?: "Unknown Error occurred")
            }
        }


}

