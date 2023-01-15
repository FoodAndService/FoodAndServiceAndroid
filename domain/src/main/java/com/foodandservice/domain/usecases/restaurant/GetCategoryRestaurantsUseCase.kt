package com.foodandservice.domain.usecases.restaurant

import com.foodandservice.domain.model.Restaurant
import com.foodandservice.domain.repository.RestaurantRepository
import com.foodandservice.domain.util.Resource

class GetCategoryRestaurantsUseCase(private val restaurantRepository: RestaurantRepository) {
    suspend operator fun invoke(category: String): Resource<List<Restaurant>> {
        return restaurantRepository.getCategoryRestaurants(category = category)
    }
}