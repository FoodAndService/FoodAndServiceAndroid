package com.foodandservice.presentation.ui.review_create

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

abstract class ReviewCreateViewModel : ViewModel() {
    sealed class State {
        object Success : State()
        object TooFewCharactersError : State()
    }

    abstract fun sendReview()
    abstract fun getState(): LiveData<State>
}