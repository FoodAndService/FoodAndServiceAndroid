package com.foodandservice.presentation.ui.review_create

sealed class ReviewCreateState {
    object Success : ReviewCreateState()
    object Empty : ReviewCreateState()
    data class Error(val message: String) : ReviewCreateState()
}