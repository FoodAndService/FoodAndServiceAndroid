package com.foodandservice.domain.usecases.restaurant

import com.foodandservice.domain.model.CategoryRestaurants
import com.foodandservice.domain.repository.RestaurantRepository
import com.foodandservice.domain.util.Resource

class GetRestaurantsUseCase(private val restaurantRepository: RestaurantRepository) {
    suspend operator fun invoke(): Resource<List<CategoryRestaurants>> {
        return restaurantRepository.getRestaurantsWithCategories()
    }
}