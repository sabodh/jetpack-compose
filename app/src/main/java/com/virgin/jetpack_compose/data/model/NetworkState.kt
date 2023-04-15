package com.virgin.jetpack_compose.data.model



/**
 * Used to identify the status of the network call status
 */
sealed class NetworkState{
    object Initial : com.virgin.jetpack_compose.data.model.NetworkState()
    object Loading : com.virgin.jetpack_compose.data.model.NetworkState()
//    object Success : NetworkState()
    data class Error(val _message: String) : com.virgin.jetpack_compose.data.model.NetworkState()
    data class Success<out T>(val _data: T) : com.virgin.jetpack_compose.data.model.NetworkState()

}