package com.foodandservice.domain.usecases.auth

import com.foodandservice.domain.repository.UserPreferencesRepository

class GetCurrentPhaseUseCase(private val userPreferencesRepository: UserPreferencesRepository) {
    suspend operator fun invoke(): String {
        return userPreferencesRepository.getCurrentPhase()
    }
}