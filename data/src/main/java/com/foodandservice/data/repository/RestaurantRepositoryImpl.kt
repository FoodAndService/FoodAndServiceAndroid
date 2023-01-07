package com.foodandservice.data.repository

import com.foodandservice.data.remote.service.RestarauntService
import com.foodandservice.domain.model.*
import com.foodandservice.domain.repository.RestaurantRepository
import com.foodandservice.domain.util.Resource
import java.time.LocalDateTime

class RestaurantRepositoryImpl(private val restarauntService: RestarauntService) :
    RestaurantRepository {
    override suspend fun getRestaurantsWithCategories(): Resource<List<CategoryRestaurants>> {
        return try {
            val restaurants = listOf(
                CategoryRestaurants(
                    id = "1", name = "Ofertas", restaurants = listOf(
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
                        ),
                    )
                ), CategoryRestaurants(
                    id = "2", name = "Mexicana", restaurants = listOf(
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
                        ),
                    )
                ), CategoryRestaurants(
                    id = "3", name = "Americana", restaurants = listOf(
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
                        ),
                    )
                ), CategoryRestaurants(
                    id = "4", name = "Italiana", restaurants = listOf(
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
                        ),
                    )
                ), CategoryRestaurants(
                    id = "5", name = "Española", restaurants = listOf(
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
                        ),
                    )
                )
            )
            Resource.Success(data = restaurants)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun getRestaurantTags(): Resource<List<RestaurantCategoryTag>> {
        return try {
            val restaurantTags = listOf(
                RestaurantCategoryTag(id = "1", name = "Ofertas"),
                RestaurantCategoryTag(id = "2", name = "Mexicana"),
                RestaurantCategoryTag(id = "3", name = "Americana"),
                RestaurantCategoryTag(id = "4", name = "Italiana"),
                RestaurantCategoryTag(id = "5", name = "Española")
            )
            Resource.Success(data = restaurantTags)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun getFavouriteRestaurants(): Resource<List<FavouriteRestaurant>> {
        return try {
            val favouriteRestaurants = listOf(
                FavouriteRestaurant(
                    id = "1", name = "Rosario's Burger"
                ), FavouriteRestaurant(
                    id = "2", name = "Domino's Pizza"
                ), FavouriteRestaurant(
                    id = "3", name = "Five Guys"
                ), FavouriteRestaurant(
                    id = "4", name = "Foster Hollywood"
                ), FavouriteRestaurant(
                    id = "5", name = "La Calle Burger"
                )
            )
            Resource.Success(data = favouriteRestaurants)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun getOrderHistory(): Resource<List<Order>> {
        return try {
            val orderHistory = listOf(
                Order(
                    id = "1",
                    restaurantName = "Wendy's",
                    amount = "15,00",
                    date = LocalDateTime.now()
                ), Order(
                    id = "2",
                    restaurantName = "Rosario's Burger",
                    amount = "12,00",
                    date = LocalDateTime.of(2022, 11, 14, 0, 0)
                ), Order(
                    id = "3",
                    restaurantName = "Foster Hollywood",
                    amount = "30,00",
                    date = LocalDateTime.of(2022, 11, 11, 0, 0)
                ), Order(
                    id = "4",
                    restaurantName = "La Calle Burger",
                    amount = "17,00",
                    date = LocalDateTime.of(2022, 11, 7, 0, 0)
                ), Order(
                    id = "5",
                    restaurantName = "Kalua",
                    amount = "6,00",
                    date = LocalDateTime.of(2022, 10, 3, 0, 0)
                )
            )
            Resource.Success(data = orderHistory)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }
}