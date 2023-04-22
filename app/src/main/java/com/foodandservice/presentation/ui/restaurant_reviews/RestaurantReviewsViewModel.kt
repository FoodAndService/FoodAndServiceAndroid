package com.foodandservice.presentation.ui.restaurant_reviews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodandservice.domain.usecases.restaurant.GetRestaurantReviewsUseCase
import com.foodandservice.domain.util.ApiResponse
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class RestaurantReviewsViewModel(private val getRestaurantReviewsUseCase: GetRestaurantReviewsUseCase) :
    ViewModel() {
    private val _restaurantReviewsState = MutableSharedFlow<RestaurantReviewsState>(replay = 10)
    val restaurantReviewsState: SharedFlow<RestaurantReviewsState> =
        _restaurantReviewsState.asSharedFlow()

    fun getRestaurantReviews(restaurantId: String) {
        viewModelScope.launch {
            _restaurantReviewsState.emit(RestaurantReviewsState.Loading)

            when (val result = getRestaurantReviewsUseCase()) {
                is ApiResponse.Success -> {
                    result.data?.let { restaurantReviews ->
                        _restaurantReviewsState.emit(
                            RestaurantReviewsState.Success(
                                restaurantReviews = restaurantReviews
                            )
                        )
                    }
                }

                is ApiResponse.Failure -> {
                    _restaurantReviewsState.emit(
                        RestaurantReviewsState.Error(
                            message = result.exception?.message ?: "Something went wrong"
                        )
                    )
                }
            }

            _restaurantReviewsState.emit(RestaurantReviewsState.Idle)
        }
    }
}