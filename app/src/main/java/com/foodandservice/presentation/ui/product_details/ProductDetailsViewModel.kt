package com.foodandservice.presentation.ui.product_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodandservice.domain.model.restaurant_details.RestaurantProductExtra
import com.foodandservice.domain.model.restaurant_details.RestaurantProductPrice
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

    lateinit var productCurrency: String

    var productQuantity = MutableStateFlow(1)

    var totalPrice = MutableStateFlow(0)

    fun increaseProductQuantity(restaurantProductPrice: RestaurantProductPrice) {
        if (productQuantity.value < 100) {
            productQuantity.value += 1
            totalPrice.value += restaurantProductPrice.value
        }
    }

    fun decreaseProductQuantity(restaurantProductPrice: RestaurantProductPrice) {
        if (productQuantity.value > 1) {
            productQuantity.value -= 1
            totalPrice.value -= restaurantProductPrice.value
        }
    }

    fun increaseExtraQuantity(productExtra: RestaurantProductExtra) {
        productExtra.incrementQuantity()
        totalPrice.value += productExtra.price.value
    }

    fun decreaseExtraQuantity(productExtra: RestaurantProductExtra) {
        productExtra.decrementQuantity()
        totalPrice.value -= productExtra.price.value
    }

    fun getRestaurantProductDetails(restaurantId: String, productId: String) {
        viewModelScope.launch {
            _productDetailsState.emit(ProductDetailsState.Loading)

            when (val restaurantProductDetails = getRestaurantProductDetailsUseCase(
                restaurantId = restaurantId, productId = productId
            )) {
                is ApiResponse.Success -> {
                    restaurantProductDetails.data?.let { productDetails ->
                        totalPrice.value = productDetails.price.value
                        productCurrency = productDetails.price.currency

                        _productDetailsState.emit(
                            ProductDetailsState.Success(
                                restaurantProductDetails = productDetails
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