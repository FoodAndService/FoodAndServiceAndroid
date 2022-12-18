package com.foodandservice.presentation.ui.table_booking

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TableBookingViewModel : ViewModel() {
    private val _tableBookingState =
        MutableStateFlow<TableBookingState>(TableBookingState.Idle)
    val tableBookingState: StateFlow<TableBookingState> =
        _tableBookingState.asStateFlow()

    fun reserveTable(diners: String, date: String, hour: String) {
        if (diners.isEmpty())
            _tableBookingState.value = TableBookingState.Error("Diners are empty")
        else if (date.isEmpty())
            _tableBookingState.value = TableBookingState.Error("Date is empty")
        else if (hour.isEmpty())
            _tableBookingState.value = TableBookingState.Error("Hour is empty")
        else {
            _tableBookingState.value = TableBookingState.Success
        }
    }
}