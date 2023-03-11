package com.foodandservice.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodandservice.domain.usecases.restaurant.GetRestaurantTagsUseCase
import com.foodandservice.domain.usecases.restaurant.GetRestaurantsUseCase
import com.foodandservice.domain.util.Resource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getRestaurantsUseCase: GetRestaurantsUseCase,
    private val getRestaurantTagsUseCase: GetRestaurantTagsUseCase
) : ViewModel() {
    private val _homeState = MutableSharedFlow<HomeState>(replay = 10)
    val homeState: SharedFlow<HomeState> = _homeState.asSharedFlow()

    init {
        getRestaurants()
    }

    private fun getRestaurants() {
        viewModelScope.launch {
            _homeState.emit(HomeState.Loading)

            when (val result = getRestaurantsUseCase()) {
                is Resource.Success -> {
                    when (val restaurantTags = getRestaurantTagsUseCase()) {
                        is Resource.Success -> {
                            result.data?.let { restaurants ->
                                restaurantTags.data?.let { restaurantTags ->
                                    _homeState.emit(
                                        HomeState.Success(
                                            restaurants = restaurants,
                                            restaurantCategoryTags = restaurantTags
                                        )
                                    )
                                }
                            }
                        }
                        is Resource.Failure -> {
                            _homeState.emit(
                                HomeState.Error(
                                    message = restaurantTags.exception?.message
                                        ?: "Something went wrong"
                                )
                            )
                        }
                    }
                }
                is Resource.Failure -> {
                    _homeState.emit(
                        HomeState.Error(
                            message = result.exception?.message
                                ?: "Something went wrong"
                        )
                    )
                }
            }

            _homeState.emit(HomeState.Idle)
        }
    }
}