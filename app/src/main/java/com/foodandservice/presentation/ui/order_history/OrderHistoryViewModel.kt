package com.foodandservice.presentation.ui.order_history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodandservice.domain.usecases.restaurant.GetOrderHistoryUseCase
import com.foodandservice.domain.util.ApiResponse
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class OrderHistoryViewModel(private val getOrderHistoryUseCase: GetOrderHistoryUseCase) :
    ViewModel() {
    private val _orderHistoryState = MutableSharedFlow<OrderHistoryState>(replay = 10)
    val orderHistoryState: SharedFlow<OrderHistoryState> = _orderHistoryState.asSharedFlow()

    init {
        getOrderHistory()
    }

    private fun getOrderHistory() {
        viewModelScope.launch {
            _orderHistoryState.emit(OrderHistoryState.Loading)

            when (val orders = getOrderHistoryUseCase()) {
                is ApiResponse.Success -> {
                    orders.data?.let { orderHistory ->
                        _orderHistoryState.emit(OrderHistoryState.Success(orders = orderHistory))
                    }
                }

                is ApiResponse.Failure -> {
                    _orderHistoryState.emit(
                        OrderHistoryState.Error(
                            message = orders.exception?.message ?: "Something went wrong"
                        )
                    )
                }
            }

            _orderHistoryState.emit(OrderHistoryState.Idle)
        }
    }
}