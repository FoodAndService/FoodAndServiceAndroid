package com.foodandservice.domain.usecases.restaurant

import com.foodandservice.domain.model.RestaurantDetailsExtra
import com.foodandservice.domain.repository.RestaurantRepository
import com.foodandservice.domain.util.Resource

class GetRestaurantDetailsExtraUseCase(private val restaurantRepository: RestaurantRepository) {
    suspend operator fun invoke(): Resource<RestaurantDetailsExtra> {
        return restaurantRepository.getRestaurantDetailsExtra()
    }
}