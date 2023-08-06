package com.foodandservice.domain.usecases.product

import com.foodandservice.domain.repository.CustomerRepository
import com.foodandservice.domain.repository.UserPreferencesRepository
import com.foodandservice.domain.usecases.cart.ClearCartUseCase
import com.foodandservice.domain.usecases.cart.GetOrCreateCartIdUseCase
import com.foodandservice.domain.usecases.restaurant.SaveRestaurantIdUseCase

class AddProductToCartUseCase(
    private val customerRepository: CustomerRepository,
    private val preferencesRepository: UserPreferencesRepository,
    private val clearCartUseCase: ClearCartUseCase,
    private val getOrCreateCartIdUseCase: GetOrCreateCartIdUseCase,
    private val saveRestaurantIdUseCase: SaveRestaurantIdUseCase
) {
    suspend operator fun invoke(
        restaurantId: String,
        restaurantName: String,
        productId: String,
        productQuantity: Int,
        productNote: String,
        productExtras: HashMap<String, Int>
    ): Boolean {
        val differentRestaurant = restaurantId != preferencesRepository.getRestaurantId()

        if (differentRestaurant) clearCartUseCase()

        saveRestaurantIdUseCase(restaurantId = restaurantId)
        preferencesRepository.saveRestaurantName(restaurantName = restaurantName)

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