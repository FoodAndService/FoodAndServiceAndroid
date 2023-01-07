package com.foodandservice.presentation.ui.favourites

import com.foodandservice.domain.model.FavouriteRestaurant

sealed class FavouritesState {
    data class Success(
        val favouriteRestaurants: List<FavouriteRestaurant>,
    ) : FavouritesState()

    data class Error(val message: String) : FavouritesState()
    object Loading : FavouritesState()
    object Idle : FavouritesState()
}