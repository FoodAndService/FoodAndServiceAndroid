package com.foodandservice.presentation.ui.product_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodandservice.domain.model.restaurant_details.RestaurantProductDetails
import com.foodandservice.domain.model.restaurant_details.RestaurantProductExtra
import com.foodandservice.domain.usecases.product.AddProductToCartUseCase
import com.foodandservice.domain.usecases.restaurant.GetRestaurantProductDetailsUseCase
import com.foodandservice.domain.util.ApiResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductDetailsViewModel(
    private val getRestaurantProductDetailsUseCase: GetRestaurantProductDetailsUseCase,
    private val addProductToCartUseCase: AddProductToCartUseCase
) : ViewModel() {
    private val _productDetailsState =
        MutableStateFlow<ProductDetailsState>(ProductDetailsState.Idle)
    val productDetailsState: StateFlow<ProductDetailsState> = _productDetailsState.asStateFlow()

    lateinit var productDetails: RestaurantProductDetails

    private val productExtras: HashMap<String, Int> = hashMapOf()

    var productNote = MutableStateFlow("")

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
        productExtras[productExtra.id] = productExtra.quantity
    }

    fun decreaseExtraQuantity(productExtra: RestaurantProductExtra) {
        if (productExtra.quantity > 0) {
            totalPrice.value -= productExtra.price.value
            productExtra.decrementQuantity()
            if (productExtra.quantity == 0) productExtras.remove(productExtra.id)
            else productExtras[productExtra.id] = productExtra.quantity
        }
    }

    private fun updateTotalPrice() {
        val mainProductPrice = getFinalPrice() * productQuantity.value
        val extrasPrice = productExtras.map { (id, qty) -> findExtraPriceById(id) * qty }.sum()
        totalPrice.value = mainProductPrice + extrasPrice
    }

    private fun getFinalPrice() =
        productDetails.discountedPrice.value.takeIf { productDetails.hasDiscount }
            ?: productDetails.price.value

    private fun findExtraPriceById(id: String) =
        productDetails.extras.firstOrNull { it.id == id }?.price?.value ?: 0

    fun addProductToCart(restaurantId: String, restaurantName: String) {
        viewModelScope.launch {
            _productDetailsState.emit(ProductDetailsState.LoadingAddToCart)

            if (addProductToCartUseCase(
                    restaurantId = restaurantId,
                    restaurantName = restaurantName,
                    productId = productDetails.id,
                    productQuantity = productQuantity.value,
                    productNote = productNote.value,
                    productExtras = productExtras
                )
            ) {
                resetQuantites()
                _productDetailsState.emit(ProductDetailsState.SuccessAddToCart)
            } else {
                _productDetailsState.emit(ProductDetailsState.ErrorAddToCart)
            }

            _productDetailsState.emit(ProductDetailsState.AddToCartIdle)
        }
    }

    private fun resetQuantites() {
        productQuantity.value = 1
        productNote.value = ""
        productExtras.clear()
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
                        if (!productDetails.hasStock) productQuantity.value = 0
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