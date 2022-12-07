package com.foodandservice.domain.usecases.restaurant

import com.foodandservice.domain.repository.UserPreferencesRepository

class GetRestaurantIdUseCase(private val userPreferencesRepository: UserPreferencesRepository) {
    suspend operator fun invoke(): String {
        return userPreferencesRepository.getRestaurantId()
    }
}