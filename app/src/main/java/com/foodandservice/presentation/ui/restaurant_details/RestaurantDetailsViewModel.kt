package com.foodandservice.presentation.ui.restaurant_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodandservice.domain.usecases.restaurant.GetRestaurantDetailsUseCase
import com.foodandservice.domain.usecases.restaurant.GetRestaurantProductCategoriesWithProductsUseCase
import com.foodandservice.domain.util.ApiResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RestaurantDetailsViewModel(
    private val getRestaurantDetailsUseCase: GetRestaurantDetailsUseCase,
    private val getRestaurantProductCategoriesWithProductsUseCase: GetRestaurantProductCategoriesWithProductsUseCase
) : ViewModel() {

    private val _restaurantDetailsState =
        MutableStateFlow<RestaurantDetailsState>(RestaurantDetailsState.Idle)
    val restaurantDetailsState: StateFlow<RestaurantDetailsState> =
        _restaurantDetailsState.asStateFlow()

    fun getRestaurantData(restaurantId: String) {
        viewModelScope.launch {
            _restaurantDetailsState.emit(RestaurantDetailsState.Loading)

            getRestaurantDetails(restaurantId)
            getRestaurantProducts(restaurantId)

            _restaurantDetailsState.emit(RestaurantDetailsState.Idle)
        }
    }

    private fun getRestaurantDetails(restaurantId: String) {
        viewModelScope.launch {
            when (val result = getRestaurantDetailsUseCase(restaurantId)) {
                is ApiResponse.Success -> {
                    result.data?.let { restaurantDetails ->
                        _restaurantDetailsState.emit(
                            RestaurantDetailsState.DetailsSuccess(
                                restaurantDetails = restaurantDetails
                            )
                        )
                    }
                }

                is ApiResponse.Failure -> {
                    _restaurantDetailsState.emit(
                        RestaurantDetailsState.Error(
                            message = result.exception?.message ?: "Something went wrong"
                        )
                    )
                }
            }
        }
    }

    private fun getRestaurantProducts(restaurantId: String) {
        viewModelScope.launch {
            when (val result = getRestaurantProductCategoriesWithProductsUseCase(restaurantId)) {
                is ApiResponse.Success -> {
                    result.data?.let { restaurantProductCategoriesWithProducts ->
                        _restaurantDetailsState.emit(
                            RestaurantDetailsState.ProductsWithCategoriesSuccess(
                                restaurantProductCategoriesWithProducts = restaurantProductCategoriesWithProducts
                            )
                        )
                    }
                }

                is ApiResponse.Failure -> {
                    _restaurantDetailsState.emit(
                        RestaurantDetailsState.Error(
                            message = result.exception?.message ?: "Something went wrong"
                        )
                    )
                }
            }
        }
    }
}