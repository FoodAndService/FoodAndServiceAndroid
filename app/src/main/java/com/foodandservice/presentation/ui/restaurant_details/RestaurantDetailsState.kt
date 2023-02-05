package com.foodandservice.presentation.ui.restaurant_details

import com.foodandservice.domain.model.RestaurantDetails

sealed class RestaurantDetailsState {
    data class Success(val restaurantDetails: RestaurantDetails) : RestaurantDetailsState()
    data class Error(val message: String) : RestaurantDetailsState()
    object Loading : RestaurantDetailsState()
    object Idle : RestaurantDetailsState()
}