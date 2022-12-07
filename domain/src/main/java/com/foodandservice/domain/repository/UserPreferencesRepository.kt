package com.foodandservice.domain.repository

interface UserPreferencesRepository {
    suspend fun saveUserAuthToken(authToken: String)
    suspend fun getUserAuthToken(): String
    suspend fun isUserLoggedIn(): Boolean
    suspend fun saveAuthCurrentPhase(currentPhase: String)
    suspend fun getAuthCurrentPhase(): String
    suspend fun finishOnboarding()
    suspend fun isOnboardingFinished(): Boolean
    suspend fun saveCartId(cartId: String)
    suspend fun getCartId(): String
    suspend fun saveRestaurantId(restaurantId: String)
    suspend fun getRestaurantId(): String
}