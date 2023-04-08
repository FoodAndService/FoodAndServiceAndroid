package com.foodandservice.domain.usecases.auth

import com.foodandservice.domain.repository.UserPreferencesRepository

class SignOutUseCase(private val preferencesRepository: UserPreferencesRepository) {
    suspend operator fun invoke() {
        preferencesRepository.deleteUserAuthToken()
        preferencesRepository.deleteAuthCurrentPhase()
        preferencesRepository.deleteRestaurantId()
        preferencesRepository.deleteCartId()
    }
}