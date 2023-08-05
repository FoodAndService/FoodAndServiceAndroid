package com.foodandservice.domain.repository

import androidx.paging.PagingData
import com.foodandservice.domain.model.Booking
import com.foodandservice.domain.model.FavouriteRestaurant
import com.foodandservice.domain.model.Order
import com.foodandservice.domain.model.OrderProduct
import com.foodandservice.domain.model.RestaurantReview
import com.foodandservice.domain.model.cart.RestaurantCart
import com.foodandservice.domain.model.location.Coordinate
import com.foodandservice.domain.model.restaurant.Restaurant
import com.foodandservice.domain.model.restaurant.RestaurantCategory
import com.foodandservice.domain.model.restaurant_details.RestaurantDetails
import com.foodandservice.domain.model.restaurant_details.RestaurantProductCategoryWithProducts
import com.foodandservice.domain.model.restaurant_details.RestaurantProductDetails
import com.foodandservice.domain.util.ApiResponse
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {
    fun getRestaurants(
        coordinate: Coordinate, restaurantCategoryId: String
    ): Flow<PagingData<Restaurant>>

    suspend fun getRestaurantCategories(): ApiResponse<List<RestaurantCategory>>
    suspend fun getRestaurantDetails(restaurantId: String): ApiResponse<RestaurantDetails>
    suspend fun getRestaurantProductCategoriesWithProducts(restaurantId: String): ApiResponse<List<RestaurantProductCategoryWithProducts>>
    suspend fun getRestaurantProductDetails(
        restaurantId: String, productId: String
    ): ApiResponse<RestaurantProductDetails>

    suspend fun getCart(cartId: String): ApiResponse<RestaurantCart>
    suspend fun addProductToCart(
        restaurantId: String,
        cartId: String,
        productId: String,
        productQuantity: Int,
        productNote: String,
        productExtras: HashMap<String, Int>
    ): Boolean

    suspend fun emptyCart()

    // Mocked
    suspend fun getFavouriteRestaurants(): ApiResponse<List<FavouriteRestaurant>>
    suspend fun getOrderHistory(): ApiResponse<List<Order>>
    suspend fun getBookings(): ApiResponse<List<Booking>>
    suspend fun getOrderProducts(): ApiResponse<List<OrderProduct>>
    suspend fun getOrderStatus(): ApiResponse<String>
    suspend fun getRestaurantReviews(): ApiResponse<List<RestaurantReview>>
}