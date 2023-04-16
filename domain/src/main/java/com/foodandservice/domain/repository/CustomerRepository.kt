package com.foodandservice.domain.repository

import androidx.paging.PagingData
import com.foodandservice.domain.model.Booking
import com.foodandservice.domain.model.CartItem
import com.foodandservice.domain.model.FavouriteRestaurant
import com.foodandservice.domain.model.Order
import com.foodandservice.domain.model.OrderProduct
import com.foodandservice.domain.model.ProductDetails
import com.foodandservice.domain.model.Restaurant
import com.foodandservice.domain.model.RestaurantCategory
import com.foodandservice.domain.model.RestaurantDetails
import com.foodandservice.domain.model.RestaurantReview
import com.foodandservice.domain.model.location.Coordinate
import com.foodandservice.domain.model.restaurant.RestaurantProductCategoryWithProducts
import com.foodandservice.domain.util.ApiResponse
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {
    fun getRestaurants(coordinate: Coordinate): Flow<PagingData<Restaurant>>
    suspend fun getRestaurantCategories(): ApiResponse<List<RestaurantCategory>>
    suspend fun getRestaurantDetails(restaurantId: String): ApiResponse<RestaurantDetails>
    suspend fun getRestaurantProductCategoriesWithProducts(restaurantId: String): ApiResponse<List<RestaurantProductCategoryWithProducts>>

    // Mock
    suspend fun getFavouriteRestaurants(): ApiResponse<List<FavouriteRestaurant>>
    suspend fun getOrderHistory(): ApiResponse<List<Order>>
    suspend fun getBookings(): ApiResponse<List<Booking>>
    suspend fun getCart(): ApiResponse<List<CartItem>>
    suspend fun getOrderProducts(): ApiResponse<List<OrderProduct>>
    suspend fun getOrderStatus(): ApiResponse<String>
    suspend fun getProductDetails(): ApiResponse<ProductDetails>
    suspend fun getRestaurantReviews(): ApiResponse<List<RestaurantReview>>
}