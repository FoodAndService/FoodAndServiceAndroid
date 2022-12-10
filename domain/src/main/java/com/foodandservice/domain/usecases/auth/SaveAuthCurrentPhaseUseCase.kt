package com.foodandservice.domain.usecases.auth

import com.foodandservice.domain.repository.UserPreferencesRepository

class SaveAuthCurrentPhaseUseCase(private val userPreferencesRepository: UserPreferencesRepository) {
    suspend operator fun invoke(currentPhase: String) {
        userPreferencesRepository.saveAuthCurrentPhase(currentPhase)
    }
}