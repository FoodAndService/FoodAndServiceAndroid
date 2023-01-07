package com.foodandservice.presentation.ui.restaurant_booking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class RestaurantBookingViewModel : ViewModel() {
    private val _restaurantBookingState = MutableSharedFlow<RestaurantBookingState>(replay = 10)
    val restaurantBookingState: SharedFlow<RestaurantBookingState> =
        _restaurantBookingState.asSharedFlow()

    fun bookRestaurantTable(diners: String, date: String, hour: String) {
        viewModelScope.launch {
            _restaurantBookingState.emit(RestaurantBookingState.Loading)

            if (diners.isEmpty()) {
                _restaurantBookingState.emit(RestaurantBookingState.Error("Diners are empty"))
            } else if (date.isEmpty()) {
                _restaurantBookingState.emit(RestaurantBookingState.Error("Date is empty"))
            } else if (hour.isEmpty()) {
                _restaurantBookingState.emit(RestaurantBookingState.Error("Hour is empty"))
            } else {
                _restaurantBookingState.emit(RestaurantBookingState.Success)
            }

            _restaurantBookingState.emit(RestaurantBookingState.Idle)
        }
    }
}