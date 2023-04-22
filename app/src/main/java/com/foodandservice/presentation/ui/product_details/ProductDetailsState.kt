package com.foodandservice.presentation.ui.product_details

import com.foodandservice.domain.model.restaurant_details.RestaurantProductDetails

sealed class ProductDetailsState {
    data class Success(val restaurantProductDetails: RestaurantProductDetails) :
        ProductDetailsState()

    data class Error(val message: String) : ProductDetailsState()
    object Loading : ProductDetailsState()
    object Idle : ProductDetailsState()
}
