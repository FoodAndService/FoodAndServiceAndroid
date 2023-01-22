package com.foodandservice.domain.usecases.order

import com.foodandservice.domain.repository.RestaurantRepository
import com.foodandservice.domain.util.Resource

class GetOrderStatusUseCase(private val restaurantRepository: RestaurantRepository) {
    suspend operator fun invoke(): Resource<String> {
        return restaurantRepository.getOrderStatus()
    }
}