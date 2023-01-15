package com.foodandservice.presentation.ui.home_category_filter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodandservice.domain.usecases.restaurant.GetCategoryRestaurantsUseCase
import com.foodandservice.domain.util.Resource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class HomeCategoryFilterViewModel(
    private val getCategoryRestaurantsUseCase: GetCategoryRestaurantsUseCase
) : ViewModel() {
    private val _homeCategoryFilterState = MutableSharedFlow<HomeCategoryFilterState>(replay = 10)
    val homeCategoryFilterState: SharedFlow<HomeCategoryFilterState> =
        _homeCategoryFilterState.asSharedFlow()

    fun getCategoryRestaurants(category: String) {
        viewModelScope.launch {
            _homeCategoryFilterState.emit(HomeCategoryFilterState.Loading)

            when (val restaurants = getCategoryRestaurantsUseCase(category = category)) {
                is Resource.Success -> {
                    restaurants.data?.let { restaurantList ->
                        _homeCategoryFilterState.emit(HomeCategoryFilterState.Success(restaurants = restaurantList))
                    }
                }
                is Resource.Failure -> {
                    _homeCategoryFilterState.emit(
                        HomeCategoryFilterState.Error(
                            message = restaurants.exception?.message ?: "Something went wrong"
                        )
                    )
                }
            }

            _homeCategoryFilterState.emit(HomeCategoryFilterState.Idle)
        }
    }
}