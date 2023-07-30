package com.foodandservice.domain.model.cart

data class RestaurantCart(
    val customerId: String,
    val id: String,
    val items: List<RestaurantCartItem>
)

sealed class RestaurantCartItem {
    data class Product(val item: RestaurantCartProduct) : RestaurantCartItem()
    data class ProductExtra(val extra: RestaurantCartProductExtra) : RestaurantCartItem()
}

data class RestaurantCartProduct(
    val discountedPrice: Int,
    val extras: List<RestaurantCartProductExtra>,
    val name: String,
    val price: Int,
    val productId: String,
    val productImage: String,
    val quantity: Int
)

data class RestaurantCartProductExtra(
    val cartItemId: String,
    val extraId: String,
    val name: String,
    val price: Int,
    val quantity: Int
)