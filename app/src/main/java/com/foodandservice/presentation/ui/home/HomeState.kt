package com.foodandservice.presentation.ui.home

import com.foodandservice.domain.model.RestaurantCategory

sealed class HomeState {
    data class SuccessRestaurantCategories(
        val restaurantCategories: List<RestaurantCategory>
    ) : HomeState()

    data class Error(val message: String) : HomeState()
    object Loading : HomeState()
    object Idle : HomeState()
}