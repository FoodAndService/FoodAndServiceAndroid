package com.foodandservice.presentation.ui.cart

import com.foodandservice.domain.model.CartItem

sealed class CartState {
    data class Success(
        val cartItems: List<CartItem>,
    ) : CartState()

    data class Error(val message: String) : CartState()
    object Loading : CartState()
    object Idle : CartState()
}