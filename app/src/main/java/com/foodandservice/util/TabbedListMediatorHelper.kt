package com.foodandservice.util

import com.foodandservice.domain.model.restaurant_details.RestaurantProductCategoryWithProducts

fun getTabbedListMediatorIndices(list: List<RestaurantProductCategoryWithProducts>): List<Int> {
    val indices = mutableListOf<Int>()
    var totalProducts = 0

    list.forEach { categoryWithProducts ->
        indices.add(totalProducts)
        totalProducts += categoryWithProducts.products.size
    }

    return indices.map { it - 1 }
}