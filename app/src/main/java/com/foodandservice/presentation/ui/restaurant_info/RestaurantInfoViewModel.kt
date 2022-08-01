package com.foodandservice.presentation.ui.restaurant_info

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class RestaurantInfoViewModel @Inject constructor() : ViewModel() {
    private val _restaurantInfoState = MutableStateFlow<RestaurantInfoState>(RestaurantInfoState.Empty)
    val restaurantInfoState: StateFlow<RestaurantInfoState> = _restaurantInfoState.asStateFlow()


}