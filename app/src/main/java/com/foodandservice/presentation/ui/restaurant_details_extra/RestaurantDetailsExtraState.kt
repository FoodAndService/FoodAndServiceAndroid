package com.foodandservice.presentation.ui.restaurant_details_extra

import com.foodandservice.domain.model.RestaurantDetailsExtra

sealed class RestaurantDetailsExtraState {
    data class Success(val restaurantDetailsExtra: RestaurantDetailsExtra) :
        RestaurantDetailsExtraState()

    data class Error(val message: String) : RestaurantDetailsExtraState()
    object Loading : RestaurantDetailsExtraState()
    object Idle : RestaurantDetailsExtraState()
}