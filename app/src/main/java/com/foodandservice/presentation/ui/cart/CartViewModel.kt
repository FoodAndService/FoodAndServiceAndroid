package com.foodandservice.presentation.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodandservice.domain.usecases.cart.GetCartUseCase
import com.foodandservice.domain.util.ApiResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CartViewModel(private val getCartUseCase: GetCartUseCase) : ViewModel() {
    private val _cartState = MutableStateFlow<CartState>(CartState.Idle)
    val cartState: StateFlow<CartState> = _cartState.asStateFlow()

    fun getCart() {
        viewModelScope.launch {
            _cartState.emit(CartState.Loading)

            getCartUseCase()?.let { cart ->
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
}