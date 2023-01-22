package com.foodandservice.domain.usecases.order

import com.foodandservice.domain.model.OrderProduct
import com.foodandservice.domain.repository.RestaurantRepository
import com.foodandservice.domain.util.Resource

class GetOrderProductsUseCase(private val restaurantRepository: RestaurantRepository) {
    suspend operator fun invoke(): Resource<List<OrderProduct>> {
        return restaurantRepository.getOrderProducts()
    }
}