package com.foodandservice.domain.usecases.restaurant

import com.foodandservice.domain.model.Booking
import com.foodandservice.domain.repository.RestaurantRepository
import com.foodandservice.domain.util.Resource

class GetBookingsUseCase(private val restaurantRepository: RestaurantRepository) {
    suspend operator fun invoke(): Resource<List<Booking>> {
        return restaurantRepository.getBookings()
    }
}