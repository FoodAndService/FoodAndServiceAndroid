package com.foodandservice.presentation.ui.product_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodandservice.domain.usecases.restaurant.GetProductDetailsUseCase
import com.foodandservice.domain.util.ApiResponse
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ProductDetailsViewModel(private val getProductDetailsUseCase: GetProductDetailsUseCase) :
    ViewModel() {
    private val _productDetailsState = MutableSharedFlow<ProductDetailsState>(replay = 10)
    val productDetailsState: SharedFlow<ProductDetailsState> = _productDetailsState.asSharedFlow()

    init {
        getProductDetails()
    }

    private fun getProductDetails() {
        viewModelScope.launch {
            _productDetailsState.emit(ProductDetailsState.Loading)

            when (val orderStatus = getProductDetailsUseCase()) {
                is ApiResponse.Success -> {
                    orderStatus.data?.let { productDetails ->
                        _productDetailsState.emit(ProductDetailsState.Success(productDetails = productDetails))
                    }
                }
                is ApiResponse.Failure -> {
                    _productDetailsState.emit(
                        ProductDetailsState.Error(
                            message = orderStatus.exception?.message ?: "Something went wrong"
                        )
                    )
                }
            }

            _productDetailsState.emit(ProductDetailsState.Idle)
        }
    }
}