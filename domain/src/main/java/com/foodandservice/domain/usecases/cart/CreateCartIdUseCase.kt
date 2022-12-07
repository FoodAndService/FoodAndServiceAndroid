package com.foodandservice.domain.usecases.cart

import com.foodandservice.domain.repository.UserPreferencesRepository
import java.util.*

class CreateCartIdUseCase(private val userPreferencesRepository: UserPreferencesRepository) {
    suspend operator fun invoke() {
        val cartId = UUID.randomUUID().toString()
        userPreferencesRepository.saveCartId(cartId)
    }
}