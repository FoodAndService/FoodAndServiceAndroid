package com.foodandservice.domain.usecases.restaurant

import com.foodandservice.domain.repository.UserPreferencesRepository

class GetRestaurantNameUseCase(private val userPreferencesRepository: UserPreferencesRepository) {
    suspend operator fun invoke() = userPreferencesRepository.getRestaurantName()
}