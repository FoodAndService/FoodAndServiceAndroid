package com.foodandservice.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class HomeViewModelImpl : HomeViewModel() {
    private val state = MutableLiveData<State>()

    init {

    }

    override fun getState(): LiveData<State> {
        return state
    }
}