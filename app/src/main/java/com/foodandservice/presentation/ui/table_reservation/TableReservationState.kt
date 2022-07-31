package com.foodandservice.presentation.ui.table_reservation

sealed class TableReservationState {
    object Success : TableReservationState()
    object Empty : TableReservationState()
    data class Error(val message: String) : TableReservationState()
}