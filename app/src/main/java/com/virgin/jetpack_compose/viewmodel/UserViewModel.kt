package com.virgin.jetpack_compose.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virgin.jetpack_compose.model.NetworkState
import com.virgin.jetpack_compose.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserViewModel(val userRepository: UserRepository) : ViewModel() {
    private val _loginState = MutableStateFlow<NetworkState>(NetworkState.Initial)
    val loginState: StateFlow<NetworkState>
        get() = _loginState

    fun loadData(){
        loginUser()
    }

    fun loginUser() =
        viewModelScope.launch {
            try {
                _loginState.value = NetworkState.Loading
                userRepository.validateLogin().catch { e ->
                    _loginState.value = NetworkState.Error(e.message ?: "Unknown Error occurred")
                }.collect {
                    if(it.isSuccessful){
                        _loginState.value = NetworkState.Result(it.body())
                       // _loginState.value = NetworkState.Success
                    }
                    else
                        _loginState.value = NetworkState.Error(it.errorBody()?.string() ?: "Unknown Error occurred")

                }
            } catch (e: Exception) {
                _loginState.value = NetworkState.Error(e.message ?: "Unknown Error occurred")
            }
        }


}

