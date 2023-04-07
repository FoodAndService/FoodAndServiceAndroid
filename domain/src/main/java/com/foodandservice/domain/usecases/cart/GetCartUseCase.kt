package com.foodandservice.domain.usecases.cart

import com.foodandservice.domain.model.CartItem
import com.foodandservice.domain.repository.CustomerRepository
import com.foodandservice.domain.util.ApiResponse

class GetCartUseCase(private val customerRepository: CustomerRepository) {
    suspend operator fun invoke(): ApiResponse<List<CartItem>> {
        return customerRepository.getCart()
    }
}