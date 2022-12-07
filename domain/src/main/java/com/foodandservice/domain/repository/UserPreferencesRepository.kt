package com.foodandservice.domain.repository

interface UserPreferencesRepository {
    suspend fun saveUserAuthToken(authToken: String)
    suspend fun getUserAuthToken(): String
    suspend fun isUserLoggedIn(): Boolean
    suspend fun saveAuthCurrentPhase(currentPhase: String)
    suspend fun getAuthCurrentPhase(): String
    suspend fun finishOnboarding()
    suspend fun isOnboardingFinished(): Boolean
    suspend fun saveCustomerCart(cartId: String)
    suspend fun getCustomerCart(): String
}