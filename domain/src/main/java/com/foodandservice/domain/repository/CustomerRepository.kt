package com.foodandservice.domain.repository

import androidx.paging.PagingData
import com.foodandservice.domain.model.*
import com.foodandservice.domain.model.location.Coordinate
import com.foodandservice.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {
    fun getRestaurants(coordinate: Coordinate): Flow<PagingData<Restaurant>>
    suspend fun getRestaurantCategories(): Resource<List<RestaurantCategory>>
    suspend fun getFavouriteRestaurants(): Resource<List<FavouriteRestaurant>>
    suspend fun getOrderHistory(): Resource<List<Order>>
    suspend fun getBookings(): Resource<List<Booking>>
    suspend fun getCart(): Resource<List<CartItem>>
    suspend fun getOrderProducts(): Resource<List<OrderProduct>>
    suspend fun getOrderStatus(): Resource<String>
    suspend fun getProductDetails(): Resource<ProductDetails>
    suspend fun getRestaurantDetails(): Resource<RestaurantDetails>
    suspend fun getRestaurantDetailsExtra(): Resource<RestaurantDetailsExtra>
    suspend fun getRestaurantReviews(): Resource<List<RestaurantReview>>
}