package com.foodandservice.domain.usecases.restaurant

import com.foodandservice.domain.repository.UserPreferencesRepository

class SaveRestaurantIdUseCase(private val userPreferencesRepository: UserPreferencesRepository) {
    suspend operator fun invoke(restaurantId: String) {
        userPreferencesRepository.saveRestaurantId(restaurantId)
    }
}