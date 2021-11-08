package com.foodandservice.ui.table_reservation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class TableReservationViewModelImpl : TableReservationViewModel() {
    private val state = MutableLiveData<State>()

    override fun reserve(diners: String, date: String, hour: String) {
        if (diners.isEmpty())
            state.value = State.DinersEmptyError
        else if (date.isEmpty())
            state.value = State.DateEmptyError
        else if (date.isEmpty())
            state.value = State.HourEmptyError
        else {
            state.value = State.Success
        }
    }

    override fun getState(): LiveData<State> {
        return state
    }
}