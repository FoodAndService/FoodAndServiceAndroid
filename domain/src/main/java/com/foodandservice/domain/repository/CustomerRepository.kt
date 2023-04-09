package com.foodandservice.domain.repository

import androidx.paging.PagingData
import com.foodandservice.domain.model.*
import com.foodandservice.domain.model.location.Coordinate
import com.foodandservice.domain.util.ApiResponse
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {
    fun getRestaurants(coordinate: Coordinate): Flow<PagingData<Restaurant>>
    suspend fun getRestaurantCategories(): ApiResponse<List<RestaurantCategory>>
    suspend fun getFavouriteRestaurants(): ApiResponse<List<FavouriteRestaurant>>
    suspend fun getOrderHistory(): ApiResponse<List<Order>>
    suspend fun getBookings(): ApiResponse<List<Booking>>
    suspend fun getCart(): ApiResponse<List<CartItem>>
    suspend fun getOrderProducts(): ApiResponse<List<OrderProduct>>
    suspend fun getOrderStatus(): ApiResponse<String>
    suspend fun getProductDetails(): ApiResponse<ProductDetails>
    suspend fun getRestaurantDetails(restaurantId: String): ApiResponse<RestaurantDetails>
    suspend fun getRestaurantReviews(): ApiResponse<List<RestaurantReview>>
}