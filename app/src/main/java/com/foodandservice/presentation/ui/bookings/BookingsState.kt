package com.foodandservice.presentation.ui.bookings

import com.foodandservice.domain.model.Booking

sealed class BookingsState {
    data class Success(
        val bookings: List<Booking>,
    ) : BookingsState()

    data class Error(val message: String) : BookingsState()
    object Loading : BookingsState()
    object Idle : BookingsState()
}