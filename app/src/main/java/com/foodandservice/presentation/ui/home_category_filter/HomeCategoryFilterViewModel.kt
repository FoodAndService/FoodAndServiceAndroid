package com.foodandservice.presentation.ui.home_category_filter

import androidx.lifecycle.ViewModel
import com.foodandservice.domain.model.Restaurant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeCategoryFilterViewModel : ViewModel() {
    private val _homeCategoryFilterState =
        MutableStateFlow<HomeCategoryFilterState>(HomeCategoryFilterState.Idle)
    val homeCategoryFilterState: StateFlow<HomeCategoryFilterState> = _homeCategoryFilterState

    fun getRestaurantsByCategory(category: String) {
        val restaurants = listOf(
            Restaurant(
                id = "1",
                name = "Rosario's Burger",
                rating = 2f,
                distance = 300,
            ),
            Restaurant(
                id = "2",
                name = "Domino's Pizza",
                rating = 4f,
                distance = 200,
            ),
            Restaurant(
                id = "3",
                name = "Five Guys",
                rating = 3f,
                distance = 900,
            )
        )

        _homeCategoryFilterState.value = HomeCategoryFilterState.Success(restaurants = restaurants)
    }
}