package com.foodandservice.domain.usecases.onboarding

import com.foodandservice.domain.repository.UserPreferencesRepository
import javax.inject.Inject

class IsOnboardingFinishedUseCase @Inject constructor(private val userPreferencesRepository: UserPreferencesRepository) {
    suspend operator fun invoke(): Boolean {
        return userPreferencesRepository.isOnboardingFinished()
    }
}