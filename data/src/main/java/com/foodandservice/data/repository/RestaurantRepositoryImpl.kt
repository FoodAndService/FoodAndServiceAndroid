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

    override suspend fun getCategoryRestaurants(category: String): Resource<List<Restaurant>> {
        return try {
            val restaurants = listOf(
                Restaurant(
                    id = "1",
                    name = "Rosario's Burger",
                    rating = 2f,
                    distance = 300,
                ), Restaurant(
                    id = "2",
                    name = "Domino's Pizza",
                    rating = 4f,
                    distance = 200,
                ), Restaurant(
                    id = "3",
                    name = "Five Guys",
                    rating = 3f,
                    distance = 900,
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

    override suspend fun getBookings(): Resource<List<Booking>> {
        return try {
            val bookings = listOf(
                Booking(
                    id = "1",
                    restaurantName = "Rosario's Burger",
                    diners = 1,
                    isActive = true,
                    dateTime = LocalDateTime.now()
                ), Booking(
                    id = "2",
                    restaurantName = "Foster Hollywood",
                    diners = 2,
                    isActive = true,
                    dateTime = LocalDateTime.now().minusDays(1)
                ), Booking(
                    id = "3",
                    restaurantName = "Domino's Pizza",
                    diners = 3,
                    isActive = false,
                    dateTime = LocalDateTime.now().minusDays(3)
                ), Booking(
                    id = "4",
                    restaurantName = "Kanival Burger",
                    diners = 4,
                    isActive = false,
                    dateTime = LocalDateTime.now().minusDays(5)
                ), Booking(
                    id = "5",
                    restaurantName = "G.O.A.T Burger",
                    diners = 5,
                    isActive = false,
                    dateTime = LocalDateTime.now().minusDays(7)
                )
            )
            Resource.Success(data = bookings)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun getCart(): Resource<List<CartItem>> {
        return try {
            val cartItems = listOf(
                CartItem("1", "Pepsi", "", "1,99", 1, false),
                CartItem("2", "Copa de vino", "", "2,99", 1, false),
                CartItem("3", "Patatas fritas", "", "0,99", 1, true),
                CartItem("4", "Pollo frito", "", "3,99", 1, false),
                CartItem("5", "Patatas fritas", "", "0,99", 1, true),
                CartItem("6", "Patatas gajo", "", "0,99", 1, true),
            )
            Resource.Success(data = cartItems)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun getOrderProducts(): Resource<List<OrderProduct>> {
        return try {
            val orderProducts = listOf(
                OrderProduct("1", "Pepsi", "", "1,99", false),
                OrderProduct("2", "Copa de vino", "", "2,99", false),
                OrderProduct("3", "Patatas fritas", "", "0,99", true),
                OrderProduct("4", "Pollo frito", "", "3,99", false),
                OrderProduct("5", "Patatas fritas", "", "0,99", true),
                OrderProduct("6", "Patatas gajo", "", "0,99", true),
                OrderProduct("7", "Pepsi", "", "1,99", false),
                OrderProduct("8", "Copa de vino", "", "2,99", false),
                OrderProduct("9", "Patatas fritas", "", "0,99", true),
                OrderProduct("10", "Pollo frito", "", "3,99", false)
            )
            Resource.Success(data = orderProducts)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun getOrderStatus(): Resource<String> {
        return try {
            val orderStatus = listOf(
                "CHECKING_PAYMENT",
                "FINISHED",
                "ORDER_DECLINED",
                "PAYMENT DECLINED",
                "PREPARING",
                "WAITING_CONFIRMATION"
            )
            Resource.Success(data = orderStatus.random())
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }
}