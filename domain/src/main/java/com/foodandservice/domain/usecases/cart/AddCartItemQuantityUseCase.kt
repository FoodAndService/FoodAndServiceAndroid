package com.foodandservice.domain.usecases.cart

import com.foodandservice.domain.model.cart.RestaurantCartItem
import com.foodandservice.domain.repository.CustomerRepository
import com.foodandservice.domain.usecases.restaurant.GetRestaurantIdUseCase

class AddCartItemQuantityUseCase(
    private val customerRepository: CustomerRepository,
    private val getOrCreateCartIdUseCase: GetOrCreateCartIdUseCase,
    private val getRestaurantIdUseCase: GetRestaurantIdUseCase,
) {
    suspend operator fun invoke(restaurantCartItem: RestaurantCartItem): Boolean {
        getRestaurantIdUseCase()?.let {
            return customerRepository.addCartItemQuantity(
                cartId = getOrCreateCartIdUseCase(),
                restaurantId = it,
                restaurantCartItem = restaurantCartItem
            )
        }
        return false
    }
}