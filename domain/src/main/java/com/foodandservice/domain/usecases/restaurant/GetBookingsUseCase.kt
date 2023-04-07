package com.foodandservice.domain.usecases.restaurant

import com.foodandservice.domain.model.Booking
import com.foodandservice.domain.repository.CustomerRepository
import com.foodandservice.domain.util.ApiResponse

class GetBookingsUseCase(private val customerRepository: CustomerRepository) {
    suspend operator fun invoke(): ApiResponse<List<Booking>> {
        return customerRepository.getBookings()
    }
}