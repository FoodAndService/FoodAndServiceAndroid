package com.foodandservice.domain.model.cart

data class RestaurantCart(
    val customerId: String, val id: String, val items: List<RestaurantCartItem>
) {
    val totalPriceUI: String
        get() {
            var totalPrice = 0

            items.forEach { cartItem ->
                totalPrice += when (cartItem) {
                    is RestaurantCartItem.Product -> {
                        val finalPrice =
                            cartItem.item.discountedPrice.takeIf { cartItem.item.hasDiscount }
                                ?: cartItem.item.price
                        finalPrice * cartItem.item.quantity
                    }

                    is RestaurantCartItem.ProductExtra -> {
                        cartItem.extra.price * cartItem.extra.quantity
                    }
                }
            }

            return String.format("%.2f€", totalPrice / 100.0)
        }
}

sealed class RestaurantCartItem {
    data class Product(val item: RestaurantCartProduct) : RestaurantCartItem()
    data class ProductExtra(val extra: RestaurantCartProductExtra) : RestaurantCartItem()
}

data class RestaurantCartProduct(
    val id: String,
    val productId: String,
    val name: String,
    val quantity: Int,
    val productNote: String,
    val price: Int,
    val discountedPrice: Int,
    val productImage: String,
    val hasDiscount: Boolean,
    val extras: List<RestaurantCartProductExtra>,
) {
    val discountedPriceUI = String.format("%.2f€", discountedPrice / 100.0)
    val priceUI = String.format("%.2f€", price / 100.0)
    val priceTotalUI: String
        get() {
            val productPrice = if (hasDiscount) discountedPrice else price
            val extrasTotalPrice = extras.sumOf { it.price * it.quantity }
            val totalInCents = productPrice * quantity + extrasTotalPrice
            val totalInEuros = totalInCents / 100.0
            return String.format("%.2f€", totalInEuros)
        }
}

data class RestaurantCartProductExtra(
    val cartItemId: String, val extraId: String, val name: String, val price: Int, val quantity: Int
) {
    val priceUI = String.format("%.2f€", price / 100.0)
}