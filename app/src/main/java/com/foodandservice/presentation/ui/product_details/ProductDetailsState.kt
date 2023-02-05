package com.foodandservice.presentation.ui.product_details

import com.foodandservice.domain.model.ProductDetails

sealed class ProductDetailsState {
    data class Success(val productDetails: ProductDetails) : ProductDetailsState()
    data class Error(val message: String) : ProductDetailsState()
    object Loading : ProductDetailsState()
    object Idle : ProductDetailsState()
}
