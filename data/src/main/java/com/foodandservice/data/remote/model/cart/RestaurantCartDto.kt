package com.foodandservice.data.remote.model.cart

import com.foodandservice.domain.model.cart.RestaurantCart
import com.foodandservice.domain.model.cart.RestaurantCartExtra
import com.foodandservice.domain.model.cart.RestaurantCartItem

data class RestaurantCartDto(
    val customerId: String,
    val id: String,
    val items: List<RestaurantCartItemDto>
)

data class RestaurantCartItemDto(
    val discountedPrice: Int,
    val extras: List<RestaurantCartExtraDto>,
    val name: String,
    val price: Int,
    val productId: String,
    val productImage: String,
    val quantity: Int
)

data class RestaurantCartExtraDto(
    val cartItemId: String,
    val extraId: String,
    val name: String,
    val price: Int,
    val quantity: Int
)

fun RestaurantCartDto.toRestaurantCart() = RestaurantCart(
    customerId = customerId,
    id = id,
    items = items.map { item -> item.toRestaurantCartItem() })

fun RestaurantCartItemDto.toRestaurantCartItem() = RestaurantCartItem(
    discountedPrice = discountedPrice,
    extras = extras.map { extra -> extra.toRestaurantCartExtra() },
    name = name,
    price = price,
    productId = productId,
    productImage = productImage,
    quantity = quantity
)

fun RestaurantCartExtraDto.toRestaurantCartExtra() = RestaurantCartExtra(
    cartItemId = cartItemId,
    extraId = extraId,
    name = name,
    price = price,
    quantity = quantity
)