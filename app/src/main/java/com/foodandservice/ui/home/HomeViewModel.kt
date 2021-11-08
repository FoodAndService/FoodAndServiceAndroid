package com.foodandservice.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

abstract class HomeViewModel : ViewModel() {
    sealed class State

    abstract fun getState(): LiveData<State>
}