package com.foodandservice.domain.usecases.restaurant

import com.foodandservice.domain.model.Order
import com.foodandservice.domain.repository.CustomerRepository
import com.foodandservice.domain.util.Resource

class GetOrderHistoryUseCase(private val customerRepository: CustomerRepository) {
    suspend operator fun invoke(): Resource<List<Order>> {
        return customerRepository.getOrderHistory()
    }
}