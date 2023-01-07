package com.foodandservice.presentation.ui.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodandservice.domain.usecases.restaurant.GetFavouriteRestaurantsUseCase
import com.foodandservice.domain.util.Resource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class FavouritesViewModel(private val getFavouriteRestaurantsUseCase: GetFavouriteRestaurantsUseCase) :
    ViewModel() {
    private val _favouritesState = MutableSharedFlow<FavouritesState>(replay = 10)
    val favouritesState: SharedFlow<FavouritesState> = _favouritesState.asSharedFlow()

    init {
        getFavouriteRestaurants()
    }

    private fun getFavouriteRestaurants() {
        viewModelScope.launch {
            _favouritesState.emit(FavouritesState.Loading)

            when (val favouriteRestaurants = getFavouriteRestaurantsUseCase()) {
                is Resource.Success -> {
                    favouriteRestaurants.data?.let { restaurants ->
                        _favouritesState.emit(FavouritesState.Success(favouriteRestaurants = restaurants))
                    }
                }
                is Resource.Failure -> {
                    _favouritesState.emit(
                        FavouritesState.Error(
                            message = favouriteRestaurants.exception?.message
                                ?: "Something went wrong"
                        )
                    )
                }
            }

            _favouritesState.emit(FavouritesState.Idle)
        }
    }
}