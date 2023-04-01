package com.foodandservice.domain.usecases.order

import com.foodandservice.domain.repository.CustomerRepository
import com.foodandservice.domain.util.Resource

class GetOrderStatusUseCase(private val customerRepository: CustomerRepository) {
    suspend operator fun invoke(): Resource<String> {
        return customerRepository.getOrderStatus()
    }
}