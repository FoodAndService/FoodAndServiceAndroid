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
        val CUSTOMER_AUTH_TOKEN = stringPreferencesKey("customer_auth_token")
        val CUSTOMER_CURRENT_PHASE = stringPreferencesKey("customer_auth_current_phase")
        val CUSTOMER_ONBOARDING_FINISHED = booleanPreferencesKey("customer_is_onboarding_finished")
        val CUSTOMER_CART_ID = stringPreferencesKey("customer_cart_id")
    }

    private object AuthCurrentPhase {
        const val AUTH_CURRENT_PHASE_INFO_ADDED = "info_added"
    }

    override suspend fun saveUserAuthToken(authToken: String) {
        dataStore.edit {
            it[Keys.CUSTOMER_AUTH_TOKEN] = authToken
        }
    }

    override suspend fun getUserAuthToken() =
        dataStore.data.map { it[Keys.CUSTOMER_AUTH_TOKEN] ?: "" }.first()

    override suspend fun isUserLoggedIn() =
        dataStore.data.map { it[Keys.CUSTOMER_AUTH_TOKEN] ?: "" }.first()
            .isNotEmpty() && dataStore.data.map { it[Keys.CUSTOMER_CURRENT_PHASE] ?: "" }
            .first() == AuthCurrentPhase.AUTH_CURRENT_PHASE_INFO_ADDED

    override suspend fun saveAuthCurrentPhase(currentPhase: String) {
        dataStore.edit {
            it[Keys.CUSTOMER_CURRENT_PHASE] = currentPhase
        }
    }

    override suspend fun getAuthCurrentPhase() =
        dataStore.data.map { it[Keys.CUSTOMER_CURRENT_PHASE] ?: "" }.first()

    override suspend fun finishOnboarding() {
        dataStore.edit {
            it[Keys.CUSTOMER_ONBOARDING_FINISHED] = true
        }
    }

    override suspend fun isOnboardingFinished() =
        dataStore.data.map { it[Keys.CUSTOMER_ONBOARDING_FINISHED] ?: false }.first()

    override suspend fun saveCustomerCart(cartId: String) {
        dataStore.edit {
            it[Keys.CUSTOMER_CART_ID] = cartId
        }
    }

    override suspend fun getCustomerCart() =
        dataStore.data.map { it[Keys.CUSTOMER_CART_ID] ?: "" }.first()
}