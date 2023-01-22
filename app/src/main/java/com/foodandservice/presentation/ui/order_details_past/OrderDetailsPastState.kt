package com.foodandservice.presentation.ui.order_details_past

import com.foodandservice.domain.model.OrderProduct

sealed class OrderDetailsPastState {
    data class Success(val order: List<OrderProduct>) : OrderDetailsPastState()
    data class Error(val message: String) : OrderDetailsPastState()
    object Loading : OrderDetailsPastState()
    object Idle : OrderDetailsPastState()
}