package com.foodandservice.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.foodandservice.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class UserPreferencesRepositoryImpl(private val dataStore: DataStore<Preferences>) :
    UserPreferencesRepository {

    private object Keys {
        val AUTH_TOKEN = stringPreferencesKey("auth_token")
        val AUTH_CURRENT_PHASE = stringPreferencesKey("auth_current_phase")
        val ONBOARDING_FINISHED = booleanPreferencesKey("is_onboarding_finished")
        val CART_ID = stringPreferencesKey("cart_id")
        val RESTAURANT_ID = stringPreferencesKey("restaurant_id")
    }

    private object AuthCurrentPhase {
        const val AUTH_CURRENT_PHASE_INFO_ADDED = "info_added"
    }

    override suspend fun saveUserAuthToken(token: String) {
        dataStore.edit {
            it[Keys.AUTH_TOKEN] = token
        }
    }

    override suspend fun getUserAuthToken() =
        dataStore.data.map { it[Keys.AUTH_TOKEN] ?: "" }.first()

    override suspend fun isUserLoggedIn() = dataStore.data.map { it[Keys.AUTH_TOKEN] ?: "" }.first()
        .isNotEmpty() && dataStore.data.map { it[Keys.AUTH_CURRENT_PHASE] ?: "" }
        .first() == AuthCurrentPhase.AUTH_CURRENT_PHASE_INFO_ADDED

    override suspend fun saveAuthCurrentPhase(currentPhase: String) {
        dataStore.edit {
            it[Keys.AUTH_CURRENT_PHASE] = currentPhase
        }
    }

    override suspend fun getAuthCurrentPhase() =
        dataStore.data.map { it[Keys.AUTH_CURRENT_PHASE] ?: "" }.first()

    override suspend fun finishOnboarding() {
        dataStore.edit {
            it[Keys.ONBOARDING_FINISHED] = true
        }
    }

    override suspend fun isOnboardingFinished() =
        dataStore.data.map { it[Keys.ONBOARDING_FINISHED] ?: false }.first()

    override suspend fun saveCartId(cartId: String) {
        dataStore.edit {
            it[Keys.CART_ID] = cartId
        }
    }

    override suspend fun getCartId() = dataStore.data.map { it[Keys.CART_ID] ?: "" }.first()

    override suspend fun saveRestaurantId(restaurantId: String) {
        dataStore.edit {
            it[Keys.RESTAURANT_ID] = restaurantId
        }
    }

    override suspend fun getRestaurantId() =
        dataStore.data.map { it[Keys.RESTAURANT_ID] ?: "" }.first()
}