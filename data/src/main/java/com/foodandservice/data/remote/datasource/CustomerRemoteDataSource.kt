package com.foodandservice.data.remote.datasource

import androidx.paging.PagingData
import com.foodandservice.data.remote.model.restaurant.RestaurantDto
import kotlinx.coroutines.flow.Flow

interface CustomerRemoteDataSource {
    fun getRestaurants(): Flow<PagingData<RestaurantDto>>
}