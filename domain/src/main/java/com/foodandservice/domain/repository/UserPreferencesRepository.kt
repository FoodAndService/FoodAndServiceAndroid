package com.foodandservice.domain.repository

interface UserPreferencesRepository {
    suspend fun saveAuthToken(authToken: String)
    suspend fun getAuthToken(): String
    suspend fun isUserLoggedIn(): Boolean
    suspend fun saveCurrentPhase(currentPhase: String)
    suspend fun getCurrentPhase(): String
    suspend fun finishOnboarding()
    suspend fun isOnboardingFinished(): Boolean
}