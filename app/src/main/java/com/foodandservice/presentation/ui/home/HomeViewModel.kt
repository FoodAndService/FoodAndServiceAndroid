package com.foodandservice.presentation.ui.home

import androidx.lifecycle.ViewModel
import com.foodandservice.domain.model.CategoryRestaurants
import com.foodandservice.domain.model.CategoryTag
import com.foodandservice.domain.model.Restaurant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
) : ViewModel() {
    private val _homeState = MutableStateFlow<HomeState>(HomeState.Empty)
    val homeState: StateFlow<HomeState> = _homeState.asStateFlow()

    init {
        obtainRestaurantsAndTags()
    }

    private fun obtainRestaurantsAndTags() {
        val restaurantList = listOf(
            CategoryRestaurants(
                1,
                "Ofertas",
                listOf(
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
                    ),
                )
            ),
            CategoryRestaurants(
                2,
                "Mexicana",
                listOf(
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
                    ),
                )
            ),
            CategoryRestaurants(
                3,
                "Americana",
                listOf(
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
                    ),
                )
            ),
            CategoryRestaurants(
                4,
                "Italiana",
                listOf(
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
                    ),
                )
            ),
            CategoryRestaurants(
                5,
                "Española",
                listOf(
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
                    ),
                )
            )
        )

        val categoryTagList = listOf(
            CategoryTag(1, "Ofertas"),
            CategoryTag(2, "Mexicana"),
            CategoryTag(3, "Americana"),
            CategoryTag(4, "Italiana"),
            CategoryTag(5, "Española")
        )

        _homeState.value =
            HomeState.Success(restaurants = restaurantList, categoryTags = categoryTagList)
    }
}