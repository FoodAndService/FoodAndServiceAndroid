package com.foodandservice.presentation.ui.order_details_current

import com.foodandservice.domain.model.OrderProduct

sealed class OrderDetailsCurrentState {
    data class Success(val order: List<OrderProduct>) : OrderDetailsCurrentState()
    data class Error(val message: String) : OrderDetailsCurrentState()
    object Loading : OrderDetailsCurrentState()
    object Idle : OrderDetailsCurrentState()
}