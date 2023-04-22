package com.foodandservice.presentation.ui.order_details_past

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodandservice.domain.usecases.order.GetOrderProductsUseCase
import com.foodandservice.domain.util.ApiResponse
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class OrderDetailsPastViewModel(
    private val getOrderProductsUseCase: GetOrderProductsUseCase
) : ViewModel() {
    private val _orderDetailsPastViewModel =
        MutableSharedFlow<OrderDetailsPastState>(replay = 10)
    val orderDetailsPastViewModel: SharedFlow<OrderDetailsPastState> =
        _orderDetailsPastViewModel.asSharedFlow()

    init {
        getOrder()
    }

    private fun getOrder() {
        viewModelScope.launch {
            _orderDetailsPastViewModel.emit(OrderDetailsPastState.Loading)

            when (val orderProducts = getOrderProductsUseCase()) {
                is ApiResponse.Success -> {
                    orderProducts.data?.let { order ->
                        _orderDetailsPastViewModel.emit(OrderDetailsPastState.Success(order = order))
                    }
                }

                is ApiResponse.Failure -> {
                    _orderDetailsPastViewModel.emit(
                        OrderDetailsPastState.Error(
                            message = orderProducts.exception?.message ?: "Something went wrong"
                        )
                    )
                }
            }

            _orderDetailsPastViewModel.emit(OrderDetailsPastState.Idle)
        }
    }
}