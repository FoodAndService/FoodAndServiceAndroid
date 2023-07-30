package com.foodandservice.domain.usecases.cart

import com.foodandservice.domain.repository.UserPreferencesRepository
import java.util.UUID

class GetOrCreateCartIdUseCase(private val userPreferencesRepository: UserPreferencesRepository) {
    suspend operator fun invoke(forceCreate: Boolean = false): String {
        val newCartId = UUID.randomUUID().toString()

        return if (forceCreate) {
            userPreferencesRepository.saveCartId(newCartId)
            newCartId
        } else {
            userPreferencesRepository.getCartId() ?: run {
                userPreferencesRepository.saveCartId(newCartId)
                newCartId
            }
        }
    }
}