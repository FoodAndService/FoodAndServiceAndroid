package com.foodandservice.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.foodandservice.domain.model.Restaurant
import com.foodandservice.domain.usecases.restaurant.GetRestaurantCategoriesUseCase
import com.foodandservice.domain.usecases.restaurant.GetRestaurantsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class HomeViewModel(
    private val getRestaurantsUseCase: GetRestaurantsUseCase,
    private val getRestaurantCategoriesUseCase: GetRestaurantCategoriesUseCase
) : ViewModel() {
    private val _homeState = MutableSharedFlow<HomeState>(replay = 10)
    val homeState: SharedFlow<HomeState> = _homeState.asSharedFlow()

    fun getRestaurants(): Flow<PagingData<Restaurant>> {
        return getRestaurantsUseCase().cachedIn(viewModelScope)
    }
}