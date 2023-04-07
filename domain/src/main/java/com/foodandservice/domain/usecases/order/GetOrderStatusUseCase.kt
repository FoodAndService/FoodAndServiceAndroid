package com.foodandservice.domain.usecases.order

import com.foodandservice.domain.repository.CustomerRepository
import com.foodandservice.domain.util.ApiResponse

class GetOrderStatusUseCase(private val customerRepository: CustomerRepository) {
    suspend operator fun invoke(): ApiResponse<String> {
        return customerRepository.getOrderStatus()
    }
}