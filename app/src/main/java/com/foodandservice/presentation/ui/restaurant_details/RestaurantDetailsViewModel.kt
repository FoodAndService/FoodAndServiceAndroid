package com.foodandservice.presentation.ui.restaurant_details

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RestaurantDetailsViewModel : ViewModel() {
    private val _restaurantInfoState =
        MutableStateFlow<RestaurantDetailsState>(RestaurantDetailsState.Idle)
    val restaurantInfoState: StateFlow<RestaurantDetailsState> = _restaurantInfoState.asStateFlow()
}