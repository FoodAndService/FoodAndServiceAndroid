package com.foodandservice.domain.usecases.auth

import com.foodandservice.domain.repository.UserPreferencesRepository

class SaveCurrentPhaseUseCase(private val userPreferencesRepository: UserPreferencesRepository) {
    suspend operator fun invoke(currentPhase: String) {
        userPreferencesRepository.saveCurrentPhase(currentPhase)
    }
}