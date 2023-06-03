package com.foodandservice.domain.usecases.restaurant

import androidx.paging.PagingData
import com.foodandservice.domain.model.location.Coordinate
import com.foodandservice.domain.model.restaurant.Restaurant
import com.foodandservice.domain.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow

class GetRestaurantsUseCase(private val customerRepository: CustomerRepository) {
    operator fun invoke(
        coordinate: Coordinate,
        restaurantCategoryId: String = ""
    ): Flow<PagingData<Restaurant>> {
        return customerRepository.getRestaurants(coordinate, restaurantCategoryId)
    }
}