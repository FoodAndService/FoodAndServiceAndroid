package com.foodandservice.presentation.ui.product_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodandservice.domain.model.restaurant_details.RestaurantProductDetails
import com.foodandservice.domain.model.restaurant_details.RestaurantProductExtra
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

    lateinit var productDetails: RestaurantProductDetails

    var productExtrasQuantities: HashMap<String, Int> = hashMapOf()

    var productQuantity = MutableStateFlow(1)

    val totalPrice = MutableStateFlow(0)

    fun increaseProductQuantity() {
        productQuantity.value += 1
        updateTotalPrice()
    }

    fun decreaseProductQuantity() {
        productQuantity.value -= 1
        updateTotalPrice()
    }

    fun increaseExtraQuantity(productExtra: RestaurantProductExtra) {
        productExtra.incrementQuantity()
        totalPrice.value += productExtra.price.value
        productExtrasQuantities[productExtra.id] = productExtra.quantity
    }

    fun decreaseExtraQuantity(productExtra: RestaurantProductExtra) {
        if (productExtra.quantity > 0) {
            totalPrice.value -= productExtra.price.value
            productExtra.decrementQuantity()
            if (productExtra.quantity == 0) productExtrasQuantities.remove(productExtra.id)
            else productExtrasQuantities[productExtra.id] = productExtra.quantity
        }
    }

    private fun getFinalPrice() =
        productDetails.discountedPrice.value.takeIf { it > 0 } ?: productDetails.price.value

    private fun updateTotalPrice() {
        val mainProductPrice = getFinalPrice() * productQuantity.value
        val extrasPrice =
            productExtrasQuantities.map { (id, qty) -> findExtraPriceById(id) * qty }.sum()
        totalPrice.value = mainProductPrice + extrasPrice
    }

    private fun findExtraPriceById(id: String): Int {
        return productDetails.extras.firstOrNull { it.id == id }?.price?.value ?: 0
    }

    fun getRestaurantProductDetails(restaurantId: String, productId: String) {
        viewModelScope.launch {
            _productDetailsState.emit(ProductDetailsState.Loading)

            when (val restaurantProductDetails = getRestaurantProductDetailsUseCase(
                restaurantId = restaurantId, productId = productId
            )) {
                is ApiResponse.Success -> {
                    restaurantProductDetails.data?.let { productDetails ->
                        this@ProductDetailsViewModel.productDetails = productDetails
                        updateTotalPrice()

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