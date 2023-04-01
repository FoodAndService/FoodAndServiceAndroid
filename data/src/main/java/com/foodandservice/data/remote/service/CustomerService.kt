package com.foodandservice.data.remote.service

import com.foodandservice.data.remote.model.restaurant.RestaurantDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CustomerService {
    @GET("business-summary")
    suspend fun getRestaurants(
        @Query("lat") latitude: Double,
        @Query("long") longitude: Double,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): List<RestaurantDto>
}