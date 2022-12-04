package com.foodandservice.data.repository

import com.foodandservice.data.remote.api.RestarauntService
import com.foodandservice.domain.repository.RestaurantRepository

class RestaurantRepositoryImpl(private val api: RestarauntService) :
    RestaurantRepository