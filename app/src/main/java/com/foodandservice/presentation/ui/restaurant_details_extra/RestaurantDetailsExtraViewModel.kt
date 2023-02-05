package com.foodandservice.presentation.ui.restaurant_details_extra

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodandservice.domain.usecases.restaurant.GetRestaurantDetailsExtraUseCase
import com.foodandservice.domain.util.Resource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class RestaurantDetailsExtraViewModel(
    private val getRestaurantDetailsExtraUseCase: GetRestaurantDetailsExtraUseCase
) : ViewModel() {
    private val _restaurantDetailsExtraState =
        MutableSharedFlow<RestaurantDetailsExtraState>(replay = 10)
    val restaurantDetailsExtraState: SharedFlow<RestaurantDetailsExtraState> =
        _restaurantDetailsExtraState.asSharedFlow()

    init {
        getRestaurantDetails()
    }

    private fun getRestaurantDetails() {
        viewModelScope.launch {
            _restaurantDetailsExtraState.emit(RestaurantDetailsExtraState.Loading)

            when (val result = getRestaurantDetailsExtraUseCase()) {
                is Resource.Success -> {
                    result.data?.let { restaurantDetailsExtra ->
                        _restaurantDetailsExtraState.emit(
                            RestaurantDetailsExtraState.Success(
                                restaurantDetailsExtra = restaurantDetailsExtra
                            )
                        )
                    }
                }
                is Resource.Failure -> {
                    _restaurantDetailsExtraState.emit(
                        RestaurantDetailsExtraState.Error(
                            message = result.exception?.message ?: "Something went wrong"
                        )
                    )
                }
            }

            _restaurantDetailsExtraState.emit(RestaurantDetailsExtraState.Idle)
        }
    }
}