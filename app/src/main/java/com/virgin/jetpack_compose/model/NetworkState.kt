package com.virgin.jetpack_compose.model



/**
 * Used to identify the status of the network call status
 */
sealed class NetworkState{
    object Initial : NetworkState()
    object Loading : NetworkState()
//    object Success : NetworkState()
    data class Error(val _message: String) : NetworkState()
    data class Success<out T>(val _data: T) : NetworkState()

}