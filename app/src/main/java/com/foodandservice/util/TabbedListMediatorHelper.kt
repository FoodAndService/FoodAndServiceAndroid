package com.foodandservice.util

import com.foodandservice.domain.model.CategoryWithProducts

fun getTabbedListMediatorIndices(list: List<CategoryWithProducts>): List<Int> {
    val indices = mutableListOf<Int>()
    indices.add(0)

    list.forEachIndexed { index, categoryWithProducts ->
        if (index > 0)
            indices.add(index * categoryWithProducts.products.size)
    }

    return indices
}