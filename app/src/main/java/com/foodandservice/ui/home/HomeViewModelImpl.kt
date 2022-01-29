package com.foodandservice.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.foodandservice.data.model.CategoryRestaurants
import com.foodandservice.data.model.CategoryTag
import com.foodandservice.data.model.Restaurant
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(): HomeViewModel() {
    private val restaurantList = MutableLiveData<List<CategoryRestaurants>>()
    private val categoryTagList = MutableLiveData<List<CategoryTag>>()
    private val state = MutableLiveData<State>()

    init {
        obtainRestaurants()
        obtainCategoryTags()
    }

    private fun obtainCategoryTags() {
        val list = listOf(
            CategoryTag(1, "Ofertas"),
            CategoryTag(2, "Mexicana"),
            CategoryTag(3, "Americana"),
            CategoryTag(4, "Italiana"),
            CategoryTag(5, "Española")
        )
        categoryTagList.value = list
    }

    private fun obtainRestaurants() {
        val list = listOf(
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
        restaurantList.value = list
    }

    fun getRestaurantList(): LiveData<List<CategoryRestaurants>> {
        return restaurantList
    }

    fun getCategoryTagList(): LiveData<List<CategoryTag>> {
        return categoryTagList
    }

    override fun getState(): LiveData<State> {
        return state
    }
}