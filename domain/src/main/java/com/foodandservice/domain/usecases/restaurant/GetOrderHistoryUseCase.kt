package com.foodandservice.domain.usecases.restaurant

import com.foodandservice.domain.model.Order
import com.foodandservice.domain.repository.RestaurantRepository
import com.foodandservice.domain.util.Resource

class GetOrderHistoryUseCase(private val restaurantRepository: RestaurantRepository) {
    suspend operator fun invoke(): Resource<List<Order>> {
        return restaurantRepository.getOrderHistory()
    }
}