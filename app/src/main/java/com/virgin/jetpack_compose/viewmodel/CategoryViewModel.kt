package com.virgin.jetpack_compose.viewmodel

import VCategory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virgin.jetpack_compose.model.NetworkState
import com.virgin.jetpack_compose.repository.CategoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val repository: CategoryRepository = CategoryRepository()
) : ViewModel() {

    // this is used to identify the network call status
    private val _categoryState = MutableStateFlow<NetworkState>(NetworkState.Initial)
    val categoryState: StateFlow<NetworkState>
        get() = _categoryState
    // this is used to hold/update the data received from network api calls
    private val _categories = MutableStateFlow<VCategory>(VCategory())
    val categories: StateFlow<VCategory>
        get() = _categories


    /**
     * Used to get the details from network call.
     */
    fun getCategory() {
        // coroutine scope is used, and expected result is in the form of flow.
        viewModelScope.launch {
            try {
                _categoryState.value = NetworkState.Loading
                repository.getCategory().catch { e ->
                    _categoryState.value = NetworkState.Error(e.message ?: "Unknown Error")
                }
                    .collect {
                        if (it.isSuccessful) {
                            _categoryState.value = NetworkState.Success(_data = it.body())
                        } else {
                            _categoryState.value =
                                NetworkState.Error(it.errorBody().toString() ?: "Unknown Error")
                        }
                    }
            } catch (e: Exception) {
                _categoryState.value = NetworkState.Error(e.message ?: "Unknown Error")
            }
        }
    }

    /**
     * This method is used to update the data flow collected from compose to the category state flow.
     * so the state flow will automatically update the stored data in particular compose components.
     */
    fun updateCategories(category: VCategory){
        _categories.value = category
    }
}