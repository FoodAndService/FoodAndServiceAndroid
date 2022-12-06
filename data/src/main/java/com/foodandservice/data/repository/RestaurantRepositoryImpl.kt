package com.foodandservice.data.repository

import com.foodandservice.data.remote.service.RestarauntService
import com.foodandservice.domain.repository.RestaurantRepository

class RestaurantRepositoryImpl(private val restarauntService: RestarauntService) :
    RestaurantRepository