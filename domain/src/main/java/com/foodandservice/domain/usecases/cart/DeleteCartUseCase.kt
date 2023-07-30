package com.foodandservice.domain.usecases.cart

import com.foodandservice.domain.repository.CustomerRepository

class DeleteCartUseCase(private val customerRepository: CustomerRepository) {
    suspend operator fun invoke() = customerRepository.deleteCart()
}