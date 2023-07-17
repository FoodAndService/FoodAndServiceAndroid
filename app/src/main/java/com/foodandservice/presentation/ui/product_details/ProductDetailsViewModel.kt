package com.foodandservice.presentation.ui.product_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodandservice.domain.usecases.restaurant.GetRestaurantProductDetailsUseCase
import com.foodandservice.domain.util.ApiResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductDetailsViewModel(private val getRestaurantProductDetailsUseCase: GetRestaurantProductDetailsUseCase) :
    ViewModel() {
    private val _productDetailsState =
        MutableStateFlow<ProductDetailsState>(ProductDetailsState.Idle)
    val productDetailsState: StateFlow<ProductDetailsState> = _productDetailsState.asStateFlow()

    var productQuantity = MutableStateFlow(1)

    fun getRestaurantProductDetails(restaurantId: String, productId: String) {
        viewModelScope.launch {
            _productDetailsState.emit(ProductDetailsState.Loading)

            when (val restaurantProductDetails = getRestaurantProductDetailsUseCase(
                restaurantId = restaurantId,
                productId = productId
            )) {
                is ApiResponse.Success -> {
                    restaurantProductDetails.data?.let {
                        _productDetailsState.emit(
                            ProductDetailsState.Success(
                                restaurantProductDetails = it
                            )
                        )
                    }
                }

                is ApiResponse.Failure -> {
                    _productDetailsState.emit(
                        ProductDetailsState.Error(
                            message = restaurantProductDetails.exception?.message
                                ?: "Something went wrong"
                        )
                    )
                }
            }

            _productDetailsState.emit(ProductDetailsState.Idle)
        }
    }
}