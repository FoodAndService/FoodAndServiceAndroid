package com.foodandservice.domain.usecases.restaurant

import com.foodandservice.domain.model.Order
import com.foodandservice.domain.repository.CustomerRepository
import com.foodandservice.domain.util.ApiResponse

class GetOrderHistoryUseCase(private val customerRepository: CustomerRepository) {
    suspend operator fun invoke(): ApiResponse<List<Order>> {
        return customerRepository.getOrderHistory()
    }
}