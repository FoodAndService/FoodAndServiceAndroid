package com.foodandservice.data.repository

import com.foodandservice.data.network.RestaurantService
import javax.inject.Inject

class RestaurantRepository @Inject constructor(private val restaurantService: RestaurantService) {

}