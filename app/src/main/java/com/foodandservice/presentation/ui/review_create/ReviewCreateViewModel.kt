package com.foodandservice.presentation.ui.review_create

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ReviewCreateViewModel : ViewModel() {
    private val _createReviewState = MutableStateFlow<ReviewCreateState>(ReviewCreateState.Success)
    val createReviewState: StateFlow<ReviewCreateState> = _createReviewState.asStateFlow()
}