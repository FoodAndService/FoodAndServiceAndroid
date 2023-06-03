package com.foodandservice.data.remote.model.cart

data class CreateUpdateRestaurantCartDto(
    val businessId: String,
    val items: List<CreateUpdateRestaurantCartItemDto>
)

data class CreateUpdateRestaurantCartItemDto(
    val extras: List<CreateUpdateRestaurantCartExtraDto>,
    val id: String,
    val productId: String,
    val quantity: Int
)

data class CreateUpdateRestaurantCartExtraDto(
    val productExtraId: String,
    val quantity: Int
)