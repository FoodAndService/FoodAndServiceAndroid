package com.foodandservice.domain.usecases.restaurant

import com.foodandservice.domain.model.FavouriteRestaurant
import com.foodandservice.domain.repository.CustomerRepository
import com.foodandservice.domain.util.ApiResponse

class GetFavouriteRestaurantsUseCase(private val customerRepository: CustomerRepository) {
    suspend operator fun invoke(): ApiResponse<List<FavouriteRestaurant>> {
        return customerRepository.getFavouriteRestaurants()
    }
}