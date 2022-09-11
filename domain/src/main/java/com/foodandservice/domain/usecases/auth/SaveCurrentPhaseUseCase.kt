package com.foodandservice.domain.usecases.auth

import com.foodandservice.domain.repository.UserPreferencesRepository
import javax.inject.Inject

class SaveCurrentPhaseUseCase @Inject constructor(private val userPreferencesRepository: UserPreferencesRepository) {
    suspend operator fun invoke(currentPhase: String) {
        userPreferencesRepository.saveCurrentPhase(currentPhase)
    }
}