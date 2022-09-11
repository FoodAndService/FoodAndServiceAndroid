package com.foodandservice.domain.usecases.auth

import com.foodandservice.domain.repository.UserPreferencesRepository
import javax.inject.Inject

class IsUserLoggedInUseCase @Inject constructor(private val userPreferencesRepository: UserPreferencesRepository) {
    suspend operator fun invoke(): Boolean {
        return userPreferencesRepository.isUserLoggedIn()
    }
}