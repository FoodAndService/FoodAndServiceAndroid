package com.foodandservice.presentation.ui.restaurant_details

import com.foodandservice.domain.model.Restaurant

sealed class RestaurantDetailsState {
    data class Success(val restaurant: Restaurant) : RestaurantDetailsState()
    data class Error(val message: String) : RestaurantDetailsState()
    object Loading : RestaurantDetailsState()
    object Idle : RestaurantDetailsState()
}