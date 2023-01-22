package com.foodandservice.presentation.ui.order_details_current

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodandservice.domain.usecases.order.GetOrderProductsUseCase
import com.foodandservice.domain.util.Resource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class OrderDetailsCurrentViewModel(
    private val getOrderProductsUseCase: GetOrderProductsUseCase
) : ViewModel() {
    private val _orderDetailsCurrentViewModel =
        MutableSharedFlow<OrderDetailsCurrentState>(replay = 10)
    val orderDetailsCurrentViewModel: SharedFlow<OrderDetailsCurrentState> =
        _orderDetailsCurrentViewModel.asSharedFlow()

    init {
        getOrder()
    }

    private fun getOrder() {
        viewModelScope.launch {
            _orderDetailsCurrentViewModel.emit(OrderDetailsCurrentState.Loading)

            when (val orderProducts = getOrderProductsUseCase()) {
                is Resource.Success -> {
                    orderProducts.data?.let { order ->
                        _orderDetailsCurrentViewModel.emit(OrderDetailsCurrentState.Success(order = order))
                    }
                }
                is Resource.Failure -> {
                    _orderDetailsCurrentViewModel.emit(
                        OrderDetailsCurrentState.Error(
                            message = orderProducts.exception?.message ?: "Something went wrong"
                        )
                    )
                }
            }

            _orderDetailsCurrentViewModel.emit(OrderDetailsCurrentState.Idle)
        }
    }
}