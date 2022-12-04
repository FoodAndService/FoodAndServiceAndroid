package com.foodandservice.domain.usecases.auth

import com.foodandservice.domain.repository.UserPreferencesRepository

class SaveUserTokenUseCase(private val userPreferencesRepository: UserPreferencesRepository) {
    suspend operator fun invoke(authToken: String) {
        userPreferencesRepository.saveAuthToken(authToken)
    }
}