package com.foodandservice.domain.usecases.order

import com.foodandservice.domain.model.OrderProduct
import com.foodandservice.domain.repository.CustomerRepository
import com.foodandservice.domain.util.ApiResponse

class GetOrderProductsUseCase(private val customerRepository: CustomerRepository) {
    suspend operator fun invoke(): ApiResponse<List<OrderProduct>> {
        return customerRepository.getOrderProducts()
    }
}