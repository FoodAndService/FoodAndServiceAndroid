package com.foodandservice.presentation.ui.restaurant_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodandservice.domain.usecases.restaurant.GetRestaurantDetailsUseCase
import com.foodandservice.domain.util.Resource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class RestaurantDetailsViewModel(
    private val getRestaurantDetailsUseCase: GetRestaurantDetailsUseCase
) : ViewModel() {
    private val _restaurantDetailsState = MutableSharedFlow<RestaurantDetailsState>(replay = 10)
    val restaurantDetailsState: SharedFlow<RestaurantDetailsState> =
        _restaurantDetailsState.asSharedFlow()

    init {
        getRestaurantDetails()
    }

    private fun getRestaurantDetails() {
        viewModelScope.launch {
            _restaurantDetailsState.emit(RestaurantDetailsState.Loading)

            when (val orderStatus = getRestaurantDetailsUseCase()) {
                is Resource.Success -> {
                    orderStatus.data?.let { restaurantDetails ->
                        _restaurantDetailsState.emit(
                            RestaurantDetailsState.Success(
                                restaurantDetails = restaurantDetails
                            )
                        )
                    }
                }
                is Resource.Failure -> {
                    _restaurantDetailsState.emit(
                        RestaurantDetailsState.Error(
                            message = orderStatus.exception?.message ?: "Something went wrong"
                        )
                    )
                }
            }

            _restaurantDetailsState.emit(RestaurantDetailsState.Idle)
        }
    }
}