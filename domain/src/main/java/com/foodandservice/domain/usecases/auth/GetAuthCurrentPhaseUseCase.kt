package com.foodandservice.domain.usecases.auth

import com.foodandservice.domain.repository.UserPreferencesRepository

class GetAuthCurrentPhaseUseCase(private val userPreferencesRepository: UserPreferencesRepository) {
    suspend operator fun invoke() = userPreferencesRepository.getAuthCurrentPhase()
}