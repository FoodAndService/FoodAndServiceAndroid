package com.foodandservice.presentation.ui.order_status

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodandservice.domain.usecases.order.GetOrderStatusUseCase
import com.foodandservice.domain.util.ApiResponse
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class OrderStatusViewModel(private val getOrderStatusUseCase: GetOrderStatusUseCase) : ViewModel() {
    private val _orderStatusState = MutableSharedFlow<OrderStatusState>(replay = 10)
    val orderStatusState: SharedFlow<OrderStatusState> = _orderStatusState.asSharedFlow()

    init {
        getOrderStatus()
    }

    private fun getOrderStatus() {
        viewModelScope.launch {
            _orderStatusState.emit(OrderStatusState.Loading)

            when (val orderStatus = getOrderStatusUseCase()) {
                is ApiResponse.Success -> {
                    orderStatus.data?.let { status ->
                        _orderStatusState.emit(OrderStatusState.Success(orderStatus = status))
                    }
                }
                is ApiResponse.Failure -> {
                    _orderStatusState.emit(
                        OrderStatusState.Error(
                            message = orderStatus.exception?.message ?: "Something went wrong"
                        )
                    )
                }
            }

            _orderStatusState.emit(OrderStatusState.Idle)
        }
    }
}