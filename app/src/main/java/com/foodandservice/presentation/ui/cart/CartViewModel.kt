package com.foodandservice.presentation.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodandservice.domain.usecases.cart.ClearCartUseCase
import com.foodandservice.domain.usecases.cart.GetCartProductsUseCase
import com.foodandservice.domain.util.ApiResponse
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class CartViewModel(
    private val getCartProductsUseCase: GetCartProductsUseCase,
    private val clearCartUseCase: ClearCartUseCase
) : ViewModel() {
    private val _cartState = MutableSharedFlow<CartState>(replay = 5)
    val cartState: SharedFlow<CartState> = _cartState.asSharedFlow()

    fun getCart() {
        viewModelScope.launch {
            _cartState.emit(CartState.Loading)

            getCartProductsUseCase()?.let { cart ->
                when (cart) {
                    is ApiResponse.Success -> {
                        cart.data?.let { restaurantCart ->
                            _cartState.emit(CartState.Success(restaurantCart = restaurantCart))
                        }
                    }

                    is ApiResponse.Failure -> {
                        _cartState.emit(
                            CartState.Error(
                                message = cart.exception?.message ?: "Something went wrong"
                            )
                        )
                    }
                }
            } ?: run {
                _cartState.emit(CartState.Empty)
            }

            _cartState.emit(CartState.Idle)
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            clearCartUseCase()
            getCart()
        }
    }
}