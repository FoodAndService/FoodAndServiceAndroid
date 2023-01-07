package com.foodandservice.domain.repository

import com.foodandservice.domain.model.CategoryRestaurants
import com.foodandservice.domain.model.FavouriteRestaurant
import com.foodandservice.domain.model.Order
import com.foodandservice.domain.model.RestaurantCategoryTag
import com.foodandservice.domain.util.Resource

interface RestaurantRepository {
    suspend fun getRestaurantsWithCategories(): Resource<List<CategoryRestaurants>>
    suspend fun getRestaurantTags(): Resource<List<RestaurantCategoryTag>>
    suspend fun getFavouriteRestaurants(): Resource<List<FavouriteRestaurant>>
    suspend fun getOrderHistory(): Resource<List<Order>>
}