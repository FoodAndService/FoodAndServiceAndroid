package com.foodandservice.presentation.ui.table_reservation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TableReservationViewModel @Inject constructor() : ViewModel() {
    private val _tableReservationState =
        MutableStateFlow<TableReservationState>(TableReservationState.Empty)
    val tableReservationState: StateFlow<TableReservationState> =
        _tableReservationState.asStateFlow()

    fun reserveTable(diners: String, date: String, hour: String) {
        if (diners.isEmpty())
            _tableReservationState.value = TableReservationState.Error("Diners are empty")
        else if (date.isEmpty())
            _tableReservationState.value = TableReservationState.Error("Date is empty")
        else if (hour.isEmpty())
            _tableReservationState.value = TableReservationState.Error("Hour is empty")
        else {
            _tableReservationState.value = TableReservationState.Success
        }
    }
}