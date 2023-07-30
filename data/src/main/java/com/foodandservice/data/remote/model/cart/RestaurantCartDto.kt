package com.foodandservice.data.remote.model.cart

import com.foodandservice.domain.model.cart.RestaurantCart
import com.foodandservice.domain.model.cart.RestaurantCartItem
import com.foodandservice.domain.model.cart.RestaurantCartProduct
import com.foodandservice.domain.model.cart.RestaurantCartProductExtra

data class RestaurantCartDto(
    val customerId: String, val id: String, val items: List<RestaurantCartItemDto>
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
    val cartItemId: String, val extraId: String, val name: String, val price: Int, val quantity: Int
)

fun RestaurantCartDto.toRestaurantCart() = RestaurantCart(
    customerId = customerId,
    id = id,
    items = items.flatMap { item -> item.toRestaurantCartItems() })

fun RestaurantCartItemDto.toRestaurantCartItems(): List<RestaurantCartItem> {
    val cartItems = mutableListOf<RestaurantCartItem>()

    val mainProduct = RestaurantCartProduct(
        discountedPrice = discountedPrice,
        extras = extras.map { it.toRestaurantCartExtra() },
        name = name,
        price = price,
        productId = productId,
        productImage = productImage,
        quantity = quantity
    )
    cartItems.add(RestaurantCartItem.Product(mainProduct))

    extras.forEach { extra ->
        cartItems.add(RestaurantCartItem.ProductExtra(extra.toRestaurantCartExtra()))
    }

    return cartItems
}

fun RestaurantCartExtraDto.toRestaurantCartExtra() = RestaurantCartProductExtra(
    cartItemId = cartItemId, extraId = extraId, name = name, price = price, quantity = quantity
)