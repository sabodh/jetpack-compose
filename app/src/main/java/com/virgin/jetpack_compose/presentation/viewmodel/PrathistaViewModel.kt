package com.virgin.jetpack_compose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virgin.jetpack_compose.data.repository.CategoryRepository
import com.virgin.jetpack_compose.domain.LazyFormEvent
import com.virgin.jetpack_compose.domain.LazyFormState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import com.virgin.jetpack_compose.data.model.NetworkState
import com.virgin.jetpack_compose.data.repository.PrathistaRepository

class PrathistaViewModel(
    private val repository: PrathistaRepository = PrathistaRepository()
) : ViewModel() {

    // this is used to identify the network call status
    private val _prathistaState = MutableStateFlow<NetworkState>(
        NetworkState.Initial)
    val prathistaState: StateFlow<NetworkState>
        get() = _prathistaState

    /**
     * Used to get the details from network call.
     */
    fun getPrathista() {
        // coroutine scope is used, and expected result is in the form of flow.
        viewModelScope.launch {
            try {
                _prathistaState.value = NetworkState.Loading
                repository.getCategory().catch { e ->
                    _prathistaState.value = NetworkState.Error(e.message ?: "Unknown Error")
                }
                    .collect {
                        if (it.isSuccessful) {
                            _prathistaState.value = NetworkState.Success(_data = it.body())
                        } else {
                            _prathistaState.value =
                                NetworkState.Error(it.errorBody().toString() ?: "Unknown Error")
                        }
                    }
            } catch (e: Exception) {
                _prathistaState.value = NetworkState.Error(e.message ?: "Unknown Error")
            }
        }
    }

}