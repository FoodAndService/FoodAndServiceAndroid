package com.foodandservice.domain.repository

interface UserPreferencesRepository {
    suspend fun saveUserAuthToken(token: String)
    suspend fun getUserAuthToken(): String?
    suspend fun deleteUserAuthToken()

    suspend fun isUserLoggedIn(): Boolean

    suspend fun saveAuthCurrentPhase(currentPhase: String)
    suspend fun getAuthCurrentPhase(): String?
    suspend fun deleteAuthCurrentPhase()

    suspend fun finishOnboarding()
    suspend fun isOnboardingFinished(): Boolean

    suspend fun saveCartId(cartId: String)
    suspend fun getCartId(): String?
    suspend fun deleteCartId()

    suspend fun saveRestaurantId(restaurantId: String)
    suspend fun saveRestaurantName(restaurantName: String)
    suspend fun getRestaurantId(): String?
    suspend fun getRestaurantName(): String?
    suspend fun deleteRestaurantId()
}