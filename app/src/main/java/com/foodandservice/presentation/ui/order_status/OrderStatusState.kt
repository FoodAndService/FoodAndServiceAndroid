package com.foodandservice.presentation.ui.order_status

sealed class OrderStatusState {
    data class Success(val orderStatus: String) : OrderStatusState()
    data class Error(val message: String) : OrderStatusState()
    object Loading : OrderStatusState()
    object Idle : OrderStatusState()
}
