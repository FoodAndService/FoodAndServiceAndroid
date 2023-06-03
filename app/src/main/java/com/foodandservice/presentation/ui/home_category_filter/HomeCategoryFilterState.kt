package com.foodandservice.presentation.ui.home_category_filter

sealed class HomeCategoryFilterState {
    data class Error(val message: String) : HomeCategoryFilterState()
    object Loading : HomeCategoryFilterState()
    object Idle : HomeCategoryFilterState()
}