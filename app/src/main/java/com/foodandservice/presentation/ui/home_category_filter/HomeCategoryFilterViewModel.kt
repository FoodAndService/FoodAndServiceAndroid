package com.foodandservice.presentation.ui.home_category_filter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.foodandservice.domain.model.location.Coordinate
import com.foodandservice.domain.model.restaurant.Restaurant
import com.foodandservice.domain.usecases.restaurant.GetRestaurantsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeCategoryFilterViewModel(
    private val getRestaurantsUseCase: GetRestaurantsUseCase
) : ViewModel() {
    private val _homeCategoryFilterState =
        MutableStateFlow<HomeCategoryFilterState>(HomeCategoryFilterState.Idle)
    val homeCategoryFilterState: StateFlow<HomeCategoryFilterState> =
        _homeCategoryFilterState.asStateFlow()

    fun getCategoryRestaurants(
        coordinate: Coordinate,
        restaurantCategoryId: String
    ): Flow<PagingData<Restaurant>> {
        _homeCategoryFilterState.value = HomeCategoryFilterState.Loading
        val restaurants = getRestaurantsUseCase(
            coordinate = coordinate,
            restaurantCategoryId = restaurantCategoryId
        ).cachedIn(viewModelScope)
        _homeCategoryFilterState.value = HomeCategoryFilterState.Idle
        return restaurants
    }
}