package com.foodandservice.ui.review_create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReviewCreateViewModelImpl @Inject constructor(): ReviewCreateViewModel() {
    private val state = MutableLiveData<State>()

    override fun sendReview() {

    }

    override fun getState(): LiveData<State> {
        return state
    }
}