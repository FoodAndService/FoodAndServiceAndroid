package com.foodandservice.presentation.ui.restaurant_info

import com.foodandservice.domain.model.Restaurant

sealed class RestaurantInfoState {
    data class Success(val restaurant: Restaurant) : RestaurantInfoState()
    data class Error(val message: String) : RestaurantInfoState()
    object Loading : RestaurantInfoState()
    object Empty : RestaurantInfoState()
}