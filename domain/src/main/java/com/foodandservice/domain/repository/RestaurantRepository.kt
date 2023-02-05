package com.foodandservice.domain.repository

import com.foodandservice.domain.model.*
import com.foodandservice.domain.util.Resource

interface RestaurantRepository {
    suspend fun getRestaurantsWithCategories(): Resource<List<CategoryRestaurants>>
    suspend fun getCategoryRestaurants(category: String): Resource<List<Restaurant>>
    suspend fun getRestaurantTags(): Resource<List<RestaurantCategoryTag>>
    suspend fun getFavouriteRestaurants(): Resource<List<FavouriteRestaurant>>
    suspend fun getOrderHistory(): Resource<List<Order>>
    suspend fun getBookings(): Resource<List<Booking>>
    suspend fun getCart(): Resource<List<CartItem>>
    suspend fun getOrderProducts(): Resource<List<OrderProduct>>
    suspend fun getOrderStatus(): Resource<String>
    suspend fun getProductDetails(): Resource<ProductDetails>
    suspend fun getRestaurantDetails(): Resource<RestaurantDetails>
    suspend fun getRestaurantDetailsExtra(): Resource<RestaurantDetailsExtra>
}