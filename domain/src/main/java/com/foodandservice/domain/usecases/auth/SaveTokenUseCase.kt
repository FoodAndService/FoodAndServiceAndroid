package com.foodandservice.domain.usecases.auth

import com.foodandservice.domain.repository.UserPreferencesRepository
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(private val userPreferencesRepository: UserPreferencesRepository) {
    suspend operator fun invoke(authToken: String) {
        userPreferencesRepository.saveAuthToken(authToken)
    }
}