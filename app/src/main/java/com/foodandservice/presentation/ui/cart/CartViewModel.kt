package com.foodandservice.presentation.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodandservice.domain.usecases.cart.GetCartUseCase
import com.foodandservice.domain.util.ApiResponse
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class CartViewModel(private val getCartUseCase: GetCartUseCase) : ViewModel() {
    private val _cartState = MutableSharedFlow<CartState>(replay = 10)
    val cartState: SharedFlow<CartState> = _cartState.asSharedFlow()

    init {
        getCart()
    }

    private fun getCart() {
        viewModelScope.launch {
            _cartState.emit(CartState.Loading)

            when (val bookings = getCartUseCase()) {
                is ApiResponse.Success -> {
                    bookings.data?.let { cartItems ->
                        _cartState.emit(CartState.Success(cartItems = cartItems))
                    }
                }

                is ApiResponse.Failure -> {
                    _cartState.emit(
                        CartState.Error(
                            message = bookings.exception?.message
                                ?: "Something went wrong"
                        )
                    )
                }
            }

            _cartState.emit(CartState.Idle)
        }
    }
}