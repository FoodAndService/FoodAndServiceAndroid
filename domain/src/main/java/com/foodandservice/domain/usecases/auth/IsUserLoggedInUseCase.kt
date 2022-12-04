package com.foodandservice.domain.usecases.auth

import com.foodandservice.domain.repository.UserPreferencesRepository

class IsUserLoggedInUseCase(private val userPreferencesRepository: UserPreferencesRepository) {
    suspend operator fun invoke(): Boolean {
        return userPreferencesRepository.isUserLoggedIn()
    }
}