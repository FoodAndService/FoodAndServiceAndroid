package com.foodandservice.domain.repository

import com.foodandservice.domain.model.CategoryRestaurants
import com.foodandservice.domain.model.RestaurantCategoryTag
import com.foodandservice.domain.util.Resource

interface RestaurantRepository {
    suspend fun getRestaurantsWithCategories(): Resource<List<CategoryRestaurants>>
    suspend fun getRestaurantTags(): Resource<List<RestaurantCategoryTag>>
}