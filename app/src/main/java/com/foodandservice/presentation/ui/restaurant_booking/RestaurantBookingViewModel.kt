package com.foodandservice.presentation.ui.restaurant_booking

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RestaurantBookingViewModel : ViewModel() {
    private val _restaurantBookingState =
        MutableStateFlow<RestaurantBookingState>(RestaurantBookingState.Idle)
    val restaurantBookingState: StateFlow<RestaurantBookingState> =
        _restaurantBookingState.asStateFlow()

    fun bookgTable(diners: String, date: String, hour: String) {
        if (diners.isEmpty())
            _restaurantBookingState.value = RestaurantBookingState.Error("Diners are empty")
        else if (date.isEmpty())
            _restaurantBookingState.value = RestaurantBookingState.Error("Date is empty")
        else if (hour.isEmpty())
            _restaurantBookingState.value = RestaurantBookingState.Error("Hour is empty")
        else {
            _restaurantBookingState.value = RestaurantBookingState.Success
        }
    }
}