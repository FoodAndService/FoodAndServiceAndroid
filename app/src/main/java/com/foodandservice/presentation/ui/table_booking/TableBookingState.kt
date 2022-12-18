package com.foodandservice.presentation.ui.table_booking

sealed class TableBookingState {
    object Success : TableBookingState()
    object Idle : TableBookingState()
    data class Error(val message: String) : TableBookingState()
}