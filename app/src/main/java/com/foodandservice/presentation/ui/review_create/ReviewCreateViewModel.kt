package com.foodandservice.presentation.ui.review_create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ReviewCreateViewModel : ViewModel() {
    private val _reviewCreateState = MutableSharedFlow<ReviewCreateState>(replay = 10)
    val reviewCreateState: SharedFlow<ReviewCreateState> = _reviewCreateState.asSharedFlow()

    fun sendReview(review: String, rating: Int) {
        viewModelScope.launch {
            _reviewCreateState.emit(ReviewCreateState.Loading)
            _reviewCreateState.emit(ReviewCreateState.Success)
            _reviewCreateState.emit(ReviewCreateState.Idle)
        }
    }
}