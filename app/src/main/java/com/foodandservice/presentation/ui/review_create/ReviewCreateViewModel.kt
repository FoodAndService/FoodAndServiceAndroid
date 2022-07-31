package com.foodandservice.presentation.ui.review_create

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ReviewCreateViewModel @Inject constructor() : ViewModel() {
    private val _createReviewState = MutableStateFlow<ReviewCreateState>(ReviewCreateState.Success)
    val createReviewState: StateFlow<ReviewCreateState> = _createReviewState.asStateFlow()
}