package com.foodandservice.domain.usecases.auth

import com.foodandservice.domain.repository.UserPreferencesRepository

class GetUserAuthTokenUseCase(private val userPreferencesRepository: UserPreferencesRepository) {
    suspend operator fun invoke() = userPreferencesRepository.getUserAuthToken()
}