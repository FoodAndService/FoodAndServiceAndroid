package com.foodandservice.data.remote.model.cart

import com.foodandservice.domain.model.cart.RestaurantCart
import com.foodandservice.domain.model.cart.RestaurantCartItem
import com.foodandservice.domain.model.cart.RestaurantCartProduct
import com.foodandservice.domain.model.cart.RestaurantCartProductExtra

data class RestaurantCartDto(
    val customerId: String, val id: String, val items: List<RestaurantCartItemDto>
)

data class RestaurantCartItemDto(
    val id: String,
    val productId: String,
    val name: String,
    val quantity: Int,
    val note: String,
    val price: Int,
    val discountedPrice: Int,
    val productImage: String,
    val hasDiscount: Boolean,
    val extras: List<RestaurantCartExtraDto>,
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

    cartItems.add(
        RestaurantCartItem.Product(
            RestaurantCartProduct(id = id,
                productId = productId,
                name = name,
                quantity = quantity,
                productNote = note,
                price = price,
                discountedPrice = discountedPrice,
                productImage = productImage,
                hasDiscount = hasDiscount,
                extras = extras.map { it.toRestaurantCartExtra() })
        )
    )

    extras.forEach { extra ->
        cartItems.add(RestaurantCartItem.ProductExtra(extra.toRestaurantCartExtra()))
    }

    return cartItems
}

fun RestaurantCartExtraDto.toRestaurantCartExtra() = RestaurantCartProductExtra(
    cartItemId = cartItemId, extraId = extraId, name = name, price = price, quantity = quantity
)