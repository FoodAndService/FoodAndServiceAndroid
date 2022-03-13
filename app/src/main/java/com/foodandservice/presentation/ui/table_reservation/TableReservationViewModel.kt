package com.foodandservice.presentation.ui.table_reservation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

abstract class TableReservationViewModel : ViewModel() {
    sealed class State {
        object Success : State()
        object DinersEmptyError : State()
        object DateEmptyError : State()
        object HourEmptyError : State()
    }

    abstract fun reserve(diners: String, date: String, hour: String)
    abstract fun getState(): LiveData<State>
}