package com.foodandservice.domain.usecases.cart

import com.foodandservice.domain.repository.CustomerRepository
import com.foodandservice.domain.repository.UserPreferencesRepository

class ClearCartUseCase(
    private val customerRepository: CustomerRepository,
    private val preferencesRepository: UserPreferencesRepository
) {
    suspend operator fun invoke() {
        preferencesRepository.deleteCartId()
        customerRepository.emptyCart()
    }
}