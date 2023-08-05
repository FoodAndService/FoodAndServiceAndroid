package com.foodandservice.domain.usecases.cart

import com.foodandservice.domain.model.cart.RestaurantCartItem
import com.foodandservice.domain.repository.CustomerRepository
import com.foodandservice.domain.usecases.restaurant.GetRestaurantIdUseCase

class SubtractCartItemQuantityUseCase(
    private val customerRepository: CustomerRepository,
    private val getOrCreateCartIdUseCase: GetOrCreateCartIdUseCase,
    private val getRestaurantIdUseCase: GetRestaurantIdUseCase,
) {
    suspend operator fun invoke(restaurantCartItem: RestaurantCartItem): Boolean {
        getRestaurantIdUseCase()?.let {
            return customerRepository.subtractCartItemQuantity(
                cartId = getOrCreateCartIdUseCase(),
                restaurantId = it,
                restaurantCartItem = restaurantCartItem
            )
        }
        return false
    }
}