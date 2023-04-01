package com.foodandservice.domain.usecases.restaurant

import androidx.paging.PagingData
import com.foodandservice.domain.model.Restaurant
import com.foodandservice.domain.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow

class GetRestaurantsUseCase(private val customerRepository: CustomerRepository) {
    operator fun invoke(): Flow<PagingData<Restaurant>> {
        return customerRepository.getRestaurants()
    }
}