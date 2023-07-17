package com.foodandservice.presentation.ui.cart

import com.foodandservice.domain.model.cart.RestaurantCart

sealed class CartState {
    data class Success(
        val restaurantCart: RestaurantCart,
    ) : CartState()

    data class Error(val message: String) : CartState()
    object Loading : CartState()
    object Empty : CartState()
    object Idle : CartState()
}