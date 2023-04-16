package com.foodandservice.data.remote.service

import com.foodandservice.data.remote.model.restaurant.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CustomerService {
    @GET("business-summary")
    suspend fun getRestaurants(
        @Query("lat") latitude: Double,
        @Query("long") longitude: Double,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): List<RestaurantDto>

    @GET("business-category")
    suspend fun getRestaurantCategories(): List<RestaurantCategoryDto>

    @GET("business-detail/{id}")
    suspend fun getRestaurantDetails(
        @Path("id") restaurantId: String
    ): RestaurantDetailsDto

    @GET("business/{restaurantId}/products")
    suspend fun getRestaurantProducts(
        @Path("restaurantId") restaurantId: String
    ): List<RestaurantProductDto>

    @GET("business/{restaurantId}/product-categories")
    suspend fun getRestaurantProductCategories(
        @Path("restaurantId") restaurantId: String
    ): List<RestaurantProductCategoryDto>
}