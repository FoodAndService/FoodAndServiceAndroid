package com.foodandservice.data.remote.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.foodandservice.data.remote.model.restaurant.RestaurantDto
import com.foodandservice.data.remote.paging.RestaurantPagingSource
import com.foodandservice.data.remote.service.CustomerService
import kotlinx.coroutines.flow.Flow

private const val NETWORK_PAGE_SIZE = 5

class CustomerRemoteDataSourceImpl(
    private val customerService: CustomerService
) : CustomerRemoteDataSource {

    override fun getRestaurants(): Flow<PagingData<RestaurantDto>> {
        return Pager(config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false
        ), pagingSourceFactory = {
            RestaurantPagingSource(customerService = customerService)
        }).flow
    }
}