package com.foodandservice.presentation.ui.restaurant_booking

sealed class RestaurantBookingState {
    object Success : RestaurantBookingState()
    object Idle : RestaurantBookingState()
    data class Error(val message: String) : RestaurantBookingState()
}