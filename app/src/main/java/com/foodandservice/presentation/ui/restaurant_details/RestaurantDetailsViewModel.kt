package com.foodandservice.presentation.ui.restaurant_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class RestaurantDetailsViewModel : ViewModel() {
    private val _restaurantDetailsState = MutableSharedFlow<RestaurantDetailsState>(replay = 10)
    val restaurantDetailsState: SharedFlow<RestaurantDetailsState> =
        _restaurantDetailsState.asSharedFlow()

    init {
        getRestaurantDetails()
    }

    private fun getRestaurantDetails() {
        viewModelScope.launch {
            _restaurantDetailsState.emit(RestaurantDetailsState.Loading)

            _restaurantDetailsState.emit(RestaurantDetailsState.Idle)
        }
    }
}