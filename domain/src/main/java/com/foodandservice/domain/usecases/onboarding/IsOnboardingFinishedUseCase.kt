package com.foodandservice.domain.usecases.onboarding

import com.foodandservice.domain.repository.UserPreferencesRepository

class IsOnboardingFinishedUseCase(private val userPreferencesRepository: UserPreferencesRepository) {
    suspend operator fun invoke(): Boolean {
        return userPreferencesRepository.isOnboardingFinished()
    }
}