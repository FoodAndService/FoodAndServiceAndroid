package com.foodandservice.data.remote.datasource

import androidx.paging.PagingData
import com.foodandservice.data.remote.model.restaurant.RestaurantDto
import com.foodandservice.domain.model.location.Coordinate
import kotlinx.coroutines.flow.Flow

interface CustomerRemoteDataSource {
    fun getRestaurants(coordinate: Coordinate): Flow<PagingData<RestaurantDto>>
}