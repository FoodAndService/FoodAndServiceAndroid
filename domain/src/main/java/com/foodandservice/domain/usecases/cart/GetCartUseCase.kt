package com.foodandservice.domain.usecases.cart

import com.foodandservice.domain.model.CartItem
import com.foodandservice.domain.repository.RestaurantRepository
import com.foodandservice.domain.util.Resource

class GetCartUseCase(private val restaurantRepository: RestaurantRepository) {
    suspend operator fun invoke(): Resource<List<CartItem>> {
        return restaurantRepository.getCart()
    }
}