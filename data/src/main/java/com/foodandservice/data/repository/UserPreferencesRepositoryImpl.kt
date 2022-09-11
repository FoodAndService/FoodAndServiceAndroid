package com.foodandservice.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.foodandservice.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferencesRepositoryImpl @Inject constructor(private val dataStore: DataStore<Preferences>) :
    UserPreferencesRepository {

    private object Keys {
        val CUSTOMER_AUTH_TOKEN = stringPreferencesKey("customer_auth_token")
        val CUSTOMER_CURRENT_PHASE = stringPreferencesKey("customer_current_phase")
        val CUSTOMER_ONBOARDING_FINISHED = booleanPreferencesKey("customer_onboarding_finished")
    }

    private object CurrentPhase {
        const val CURRENT_PHASE_PHONE_VERIFIED = "phone_verified"
        const val CURRENT_PHASE_INFO_ADDED = "info_added"
    }

    override suspend fun saveAuthToken(authToken: String) {
        dataStore.edit {
            it[Keys.CUSTOMER_AUTH_TOKEN] = authToken
        }
    }

    override suspend fun getAuthToken() =
        dataStore.data.map { it[Keys.CUSTOMER_AUTH_TOKEN] ?: "" }.first()

    override suspend fun isUserLoggedIn() =
        dataStore.data.map { it[Keys.CUSTOMER_AUTH_TOKEN] ?: "" }.first()
            .isNotEmpty() && dataStore.data.map { it[Keys.CUSTOMER_CURRENT_PHASE] ?: "" }
            .first() == CurrentPhase.CURRENT_PHASE_INFO_ADDED

    override suspend fun saveCurrentPhase(currentPhase: String) {
        dataStore.edit {
            it[Keys.CUSTOMER_CURRENT_PHASE] = currentPhase
        }
    }

    override suspend fun getCurrentPhase() =
        dataStore.data.map { it[Keys.CUSTOMER_CURRENT_PHASE] ?: "" }.first()

    override suspend fun finishOnboarding() {
        dataStore.edit {
            it[Keys.CUSTOMER_ONBOARDING_FINISHED] = true
        }
    }

    override suspend fun isOnboardingFinished() =
        dataStore.data.map { it[Keys.CUSTOMER_ONBOARDING_FINISHED] ?: false }.first()
}