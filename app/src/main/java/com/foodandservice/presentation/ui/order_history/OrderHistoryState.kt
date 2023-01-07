package com.foodandservice.presentation.ui.order_history

import com.foodandservice.domain.model.Order

sealed class OrderHistoryState {
    data class Success(val orders: List<Order>) : OrderHistoryState()
    data class Error(val message: String) : OrderHistoryState()
    object Loading : OrderHistoryState()
    object Idle : OrderHistoryState()
}
