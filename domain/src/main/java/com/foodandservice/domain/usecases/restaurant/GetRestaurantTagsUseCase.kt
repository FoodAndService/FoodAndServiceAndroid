package com.foodandservice.domain.usecases.restaurant

import com.foodandservice.domain.model.RestaurantCategoryTag
import com.foodandservice.domain.repository.RestaurantRepository
import com.foodandservice.domain.util.Resource

class GetRestaurantTagsUseCase(private val restaurantRepository: RestaurantRepository) {
    suspend operator fun invoke(): Resource<List<RestaurantCategoryTag>> {
        return restaurantRepository.getRestaurantTags()
    }
}