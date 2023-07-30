package com.foodandservice.domain.usecases.cart

import com.foodandservice.domain.model.cart.RestaurantCart
import com.foodandservice.domain.repository.CustomerRepository
import com.foodandservice.domain.repository.UserPreferencesRepository
import com.foodandservice.domain.util.ApiResponse

class GetCartProductsUseCase(
    private val customerRepository: CustomerRepository,
    private val preferencesRepository: UserPreferencesRepository
) {
    suspend operator fun invoke(): ApiResponse<RestaurantCart>? {
        preferencesRepository.getCartId()?.let { cartId ->
            return customerRepository.getCart(cartId = cartId)
        }
        return null
    }
}