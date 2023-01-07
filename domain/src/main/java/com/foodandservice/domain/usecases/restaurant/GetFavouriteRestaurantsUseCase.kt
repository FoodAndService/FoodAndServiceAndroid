package com.foodandservice.domain.usecases.restaurant

import com.foodandservice.domain.model.FavouriteRestaurant
import com.foodandservice.domain.repository.RestaurantRepository
import com.foodandservice.domain.util.Resource

class GetFavouriteRestaurantsUseCase(private val restaurantRepository: RestaurantRepository) {
    suspend operator fun invoke(): Resource<List<FavouriteRestaurant>> {
        return restaurantRepository.getFavouriteRestaurants()
    }
}