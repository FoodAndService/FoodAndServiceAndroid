package com.foodandservice.domain.usecases.product

import com.foodandservice.domain.repository.CustomerRepository
import com.foodandservice.domain.usecases.cart.GetOrCreateCartIdUseCase
import com.foodandservice.domain.usecases.restaurant.GetRestaurantIdUseCase
import com.foodandservice.domain.usecases.restaurant.SaveRestaurantIdUseCase

class AddProductToCartUseCase(
    private val customerRepository: CustomerRepository,
    private val getOrCreateCartIdUseCase: GetOrCreateCartIdUseCase,
    private val getRestaurantIdUseCase: GetRestaurantIdUseCase,
    private val saveRestaurantIdUseCase: SaveRestaurantIdUseCase
) {
    suspend operator fun invoke(
        restaurantId: String,
        productId: String,
        productQuantity: Int,
        productNote: String,
        productExtras: HashMap<String, Int>
    ): Boolean {
        val differentRestaurant = restaurantId != getRestaurantIdUseCase()

        if (differentRestaurant)
            customerRepository.deleteCart()

        saveRestaurantIdUseCase(restaurantId = restaurantId)

        return customerRepository.addProductToCart(
            restaurantId = restaurantId,
            cartId = getOrCreateCartIdUseCase(forceCreate = differentRestaurant),
            productId = productId,
            productQuantity = productQuantity,
            productNote = productNote,
            productExtras = productExtras
        )
    }
}