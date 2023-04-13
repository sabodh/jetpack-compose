package com.virgin.jetpack_compose.presentation

import VCategory
import VCategoryItem

sealed class LazyFormEvent {
    data class ItemRemoved(val categoryItem: VCategoryItem):LazyFormEvent()
    data class ItemAdded(val categoryItem: VCategoryItem):LazyFormEvent()
    data class UpdateCategory(val category: VCategory): LazyFormEvent()
    object LoadCategory: LazyFormEvent()

}