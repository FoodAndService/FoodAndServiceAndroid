package com.foodandservice.domain.usecases.cart

import com.foodandservice.domain.repository.UserPreferencesRepository

class GetCartIdUseCase(private val userPreferencesRepository: UserPreferencesRepository) {
    suspend operator fun invoke(): String {
        return userPreferencesRepository.getCartId()
    }
}