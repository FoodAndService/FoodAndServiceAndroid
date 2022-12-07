package com.foodandservice.domain.usecases.cart

import com.foodandservice.domain.repository.UserPreferencesRepository

class GetCustomerCartUseCase(private val userPreferencesRepository: UserPreferencesRepository) {
    suspend operator fun invoke(): String {
        return userPreferencesRepository.getCustomerCart()
    }
}