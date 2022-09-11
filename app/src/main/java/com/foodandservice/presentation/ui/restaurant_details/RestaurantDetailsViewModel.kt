package com.foodandservice.presentation.ui.restaurant_details

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class RestaurantDetailsViewModel @Inject constructor() : ViewModel() {
    private val _restaurantInfoState = MutableStateFlow<RestaurantDetailsState>(RestaurantDetailsState.Empty)
    val restaurantInfoState: StateFlow<RestaurantDetailsState> = _restaurantInfoState.asStateFlow()


}