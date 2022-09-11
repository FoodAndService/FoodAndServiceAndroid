package com.foodandservice.domain.usecases.onboarding

import com.foodandservice.domain.repository.UserPreferencesRepository
import javax.inject.Inject

class FinishOnboardingUseCase @Inject constructor(private val userPreferencesRepository: UserPreferencesRepository) {
    suspend operator fun invoke() {
        return userPreferencesRepository.finishOnboarding()
    }
}