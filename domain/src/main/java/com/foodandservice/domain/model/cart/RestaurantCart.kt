package com.foodandservice.domain.model.cart

data class RestaurantCart(
    val customerId: String,
    val id: String,
    val items: List<RestaurantCartItem>
)

data class RestaurantCartItem(
    val discountedPrice: Int,
    val extras: List<RestaurantCartExtra>,
    val name: String,
    val price: Int,
    val productId: String,
    val productImage: String,
    val quantity: Int
)

data class RestaurantCartExtra(
    val cartItemId: String,
    val extraId: String,
    val name: String,
    val price: Int,
    val quantity: Int
)