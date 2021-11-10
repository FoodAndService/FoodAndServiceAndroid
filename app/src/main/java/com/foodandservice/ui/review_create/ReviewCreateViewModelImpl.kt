package com.foodandservice.ui.review_create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ReviewCreateViewModelImpl : ReviewCreateViewModel() {
    private val state = MutableLiveData<State>()

    override fun sendReview() {

    }

    override fun getState(): LiveData<State> {
        return state
    }
}