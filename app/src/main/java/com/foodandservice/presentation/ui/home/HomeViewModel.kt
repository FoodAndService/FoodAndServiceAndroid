package com.foodandservice.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.birjuvachhani.locus.Locus
import com.foodandservice.domain.model.location.Coordinate
import com.foodandservice.domain.model.restaurant.Restaurant
import com.foodandservice.domain.usecases.restaurant.GetRestaurantCategoriesUseCase
import com.foodandservice.domain.usecases.restaurant.GetRestaurantsUseCase
import com.foodandservice.domain.util.ApiResponse
import com.google.android.gms.location.LocationRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getRestaurantsUseCase: GetRestaurantsUseCase,
    private val getRestaurantCategoriesUseCase: GetRestaurantCategoriesUseCase
) : ViewModel() {
    private val _homeState = MutableStateFlow<HomeState>(HomeState.Idle)
    val homeState: StateFlow<HomeState> = _homeState.asStateFlow()

    init {
        configureLocus()
    }

    private fun configureLocus() {
        Locus.configure {
            request {
                priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
                interval = 2500L
                fastestInterval = 2500L
                maxWaitTime = 5000L
            }
        }
    }

    private fun getRestaurantCategories() {
        viewModelScope.launch {
            when (val result = getRestaurantCategoriesUseCase()) {
                is ApiResponse.Success -> {
                    result.data?.let { restaurantCategories ->
                        _homeState.emit(
                            HomeState.SuccessRestaurantCategories(
                                restaurantCategories = restaurantCategories
                            )
                        )
                    }
                }

                is ApiResponse.Failure -> {
                    _homeState.emit(
                        HomeState.Error(
                            message = result.exception?.message ?: "Something went wrong"
                        )
                    )
                }
            }
        }
    }

    fun getRestaurantsWithCategories(coordinate: Coordinate): Flow<PagingData<Restaurant>> {
        _homeState.value = HomeState.Loading
        getRestaurantCategories()
        val restaurants = getRestaurantsUseCase(coordinate).cachedIn(viewModelScope)
        _homeState.value = HomeState.Idle
        return restaurants
    }
}