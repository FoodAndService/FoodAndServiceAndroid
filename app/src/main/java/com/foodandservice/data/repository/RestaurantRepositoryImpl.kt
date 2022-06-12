package com.foodandservice.data.repository

import com.foodandservice.data.remote.RestaurantApi
import com.foodandservice.domain.repository.RestaurantRepository
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(private val api: RestaurantApi) : RestaurantRepository {

}