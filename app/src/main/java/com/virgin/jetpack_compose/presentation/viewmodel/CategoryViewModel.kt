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
class CategoryViewModel(
    private val repository: CategoryRepository = CategoryRepository()
) : ViewModel() {

    // this is used to identify the network call status
    private val _categoryState = MutableStateFlow<NetworkState>(
        NetworkState.Initial)
    val categoryState: StateFlow<NetworkState>
        get() = _categoryState
    // this is used to hold/update the data received from network api calls
    private val _categories = MutableStateFlow(LazyFormState())
    val categories: StateFlow<LazyFormState>
        get() = _categories


    /**
     * Used to get the details from network call.
     */
    private fun getCategory() {
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


    fun onEvent(event: LazyFormEvent){
        when(event){
            is LazyFormEvent.ItemAdded->{

            }
            is LazyFormEvent.ItemRemoved->{
                val current = _categories.value.vCategories
                val categories = current.toMutableList().apply {
                    remove(event.categoryItem)
                }.toList()
                _categories.value = _categories.value.copy(vCategories = categories)
            }
            is LazyFormEvent.UpdateCategory->{
                /**
                 * This method is used to update the data flow collected from compose to the category state flow.
                 * so the state flow will automatically update the stored data in particular compose components.
                 */
                _categories.value =  _categories.value.copy(vCategories = event.category)
            }
            is LazyFormEvent.LoadCategory->{
                getCategory()
            }

        }
    }
}