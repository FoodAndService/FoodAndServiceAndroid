package com.foodandservice.data.remote.service

import com.foodandservice.data.remote.model.cart.CreateUpdateRestaurantCartDto
import com.foodandservice.data.remote.model.cart.RestaurantCartDto
import com.foodandservice.data.remote.model.restaurant.*
import com.foodandservice.data.remote.model.restaurant_details.RestaurantDetailsDto
import com.foodandservice.data.remote.model.restaurant_details.RestaurantProductCategoryDto
import com.foodandservice.data.remote.model.restaurant_details.RestaurantProductDetailsDto
import com.foodandservice.data.remote.model.restaurant_details.RestaurantProductDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface CustomerService {
    @GET("business-summary")
    suspend fun getRestaurants(
        @Query("lat") latitude: Double,
        @Query("long") longitude: Double,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("categoryId") categoryId: String
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

    @GET("business/{restaurantId}/product/{productId}")
    suspend fun getRestaurantProductDetails(
        @Path("restaurantId") restaurantId: String,
        @Path("productId") productId: String
    ): RestaurantProductDetailsDto

    @PUT("cart/{id}")
    suspend fun createOrUpdateCart(
        @Path("id") cartId: String,
        @Body restaurantCart: CreateUpdateRestaurantCartDto
    ): Response<Unit>

    @GET("cart/{id}")
    suspend fun getCart(
        @Path("id") cartId: String
    ): RestaurantCartDto
}