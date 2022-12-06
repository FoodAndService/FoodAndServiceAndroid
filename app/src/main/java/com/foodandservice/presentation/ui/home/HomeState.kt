package com.foodandservice.presentation.ui.home

import com.foodandservice.domain.model.CategoryRestaurants
import com.foodandservice.domain.model.CategoryTag

sealed class HomeState {
    data class Success(
        val restaurants: List<CategoryRestaurants>,
        val categoryTags: List<CategoryTag>,
    ) : HomeState()

    data class Error(val message: String) : HomeState()
    object Loading : HomeState()
    object Idle : HomeState()
}