package com.foodandservice.domain.usecases.onboarding

import com.foodandservice.domain.repository.UserPreferencesRepository

class FinishOnboardingUseCase(private val userPreferencesRepository: UserPreferencesRepository) {
    suspend operator fun invoke() {
        return userPreferencesRepository.finishOnboarding()
    }
}