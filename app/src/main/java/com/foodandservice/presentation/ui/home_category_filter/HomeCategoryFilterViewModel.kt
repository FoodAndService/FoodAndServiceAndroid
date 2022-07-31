package com.foodandservice.presentation.ui.home_category_filter

import androidx.lifecycle.ViewModel
import com.foodandservice.domain.model.Restaurant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeCategoryFilterViewModel @Inject constructor() : ViewModel() {
    private val _homeCategoryFilterState =
        MutableStateFlow<HomeCategoryFilterState>(HomeCategoryFilterState.Empty)
    val homeCategoryFilterState: StateFlow<HomeCategoryFilterState> = _homeCategoryFilterState

    fun getRestaurantsByCategory(category: String) {
        val restaurants = listOf(
            Restaurant(
                "Rosario's Burger",
                2f,
                300,
            ),
            Restaurant(
                "Domino's Pizza",
                4f,
                200,
            ),
            Restaurant(
                "Five Guys",
                3f,
                900,
            )
        )

        _homeCategoryFilterState.value = HomeCategoryFilterState.Success(restaurants = restaurants)
    }
}