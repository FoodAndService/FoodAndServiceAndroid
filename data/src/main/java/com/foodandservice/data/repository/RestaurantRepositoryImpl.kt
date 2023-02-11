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

    override suspend fun getProductDetails(): Resource<ProductDetails> {
        return try {
            val allergensAndIntolerances = listOf(
                AllergenIntolerance(id = "1", name = "Celiac"),
                AllergenIntolerance(id = "2", name = "Egg"),
                AllergenIntolerance(id = "3", name = "Milk"),
                AllergenIntolerance(id = "4", name = "Sesame"),
                AllergenIntolerance(id = "5", name = "Vegetarian")
            )

            val productExtras = listOf(
                ProductExtra(
                    id = "1", name = "Polla en vinagre", price = "69,00"
                ), ProductExtra(
                    id = "2", name = "Patatas gajo", price = "2,25"
                ), ProductExtra(
                    id = "3", name = "Batatas asadas", price = "3,00"
                ), ProductExtra(
                    id = "4", name = "Huevo frito", price = "2,50"
                ), ProductExtra(
                    id = "5", name = "Alcohol etílico", price = "1,00"
                ), ProductExtra(
                    id = "6", name = "Chicle", price = "55,00"
                ), ProductExtra(
                    id = "7", name = "Pringles", price = "2,00"
                ), ProductExtra(
                    id = "8", name = "Agua", price = "2,25"
                ), ProductExtra(
                    id = "9", name = "Snickers", price = "3,00"
                ), ProductExtra(
                    id = "10", name = "Trembolona", price = "2,50"
                )
            )
            Resource.Success(
                data = ProductDetails(
                    id = "1",
                    allergensAndIntolerances = allergensAndIntolerances,
                    productExtras = productExtras
                )
            )
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun getRestaurantDetails(): Resource<RestaurantDetails> {
        return try {
            val categoriesWithProducts = listOf(
                CategoryWithProducts(
                    categoryName = "Bebidas", products = listOf(
                        Product(
                            id = "1",
                            name = "Pepsi",
                            image = "1",
                            inStock = false,
                            isRefill = true,
                            price = "2,00"
                        ),
                        Product(
                            id = "2",
                            name = "Fanta",
                            image = "1",
                            inStock = true,
                            isRefill = true,
                            price = "2,00"
                        ),
                        Product(
                            id = "3",
                            name = "Nestea",
                            image = "1",
                            inStock = true,
                            isRefill = false,
                            price = "2,50"
                        ),
                        Product(
                            id = "4",
                            name = "Pepsi Max",
                            image = "1",
                            inStock = true,
                            isRefill = false,
                            price = "2,90"
                        ),
                        Product(
                            id = "5",
                            name = "Lágrimas",
                            image = "1",
                            inStock = false,
                            isRefill = true,
                            price = "4,20"
                        ),
                    )
                ), CategoryWithProducts(
                    categoryName = "Entrantes", products = listOf(
                        Product(
                            id = "6",
                            name = "Aceitunas verdes",
                            image = "1",
                            inStock = true,
                            isRefill = false,
                            price = "1,50"
                        ),
                        Product(
                            id = "7",
                            name = "Aceitunas negras",
                            image = "1",
                            inStock = true,
                            isRefill = false,
                            price = "1,80"
                        ),
                        Product(
                            id = "8",
                            name = "Croquetas de atún",
                            image = "1",
                            inStock = true,
                            isRefill = false,
                            price = "3,80"
                        ),
                        Product(
                            id = "9",
                            name = "Guacamole",
                            image = "1",
                            inStock = true,
                            isRefill = false,
                            price = "2,80"
                        ),
                        Product(
                            id = "10",
                            name = "Quesadillas",
                            image = "1",
                            inStock = false,
                            isRefill = false,
                            price = "2,20"
                        ),
                    )
                ), CategoryWithProducts(
                    categoryName = "Pizzas", products = listOf(
                        Product(
                            id = "11",
                            name = "Pizza carbonara",
                            image = "1",
                            inStock = true,
                            isRefill = false,
                            price = "8,50"
                        ),
                        Product(
                            id = "12",
                            name = "Pizza barbacoa",
                            image = "1",
                            inStock = true,
                            isRefill = false,
                            price = "9,80"
                        ),
                        Product(
                            id = "13",
                            name = "Pizza cuatro quesos",
                            image = "1",
                            inStock = true,
                            isRefill = false,
                            price = "7,80"
                        ),
                        Product(
                            id = "14",
                            name = "Pizza extravaganza",
                            image = "1",
                            inStock = true,
                            isRefill = false,
                            price = "9,50"
                        ),
                        Product(
                            id = "15",
                            name = "Pizza pulled pork",
                            image = "1",
                            inStock = false,
                            isRefill = false,
                            price = "12,80"
                        ),
                    )
                ), CategoryWithProducts(
                    categoryName = "Sopas", products = listOf(
                        Product(
                            id = "16",
                            name = "Gazpacho",
                            image = "1",
                            inStock = true,
                            isRefill = false,
                            price = "5,80"
                        ),
                        Product(
                            id = "17",
                            name = "Salmorejo",
                            image = "1",
                            inStock = false,
                            isRefill = false,
                            price = "5,90"
                        ),
                        Product(
                            id = "18",
                            name = "Sopa de fideos",
                            image = "1",
                            inStock = true,
                            isRefill = false,
                            price = "4,90"
                        ),
                        Product(
                            id = "19",
                            name = "Caldo de pollo",
                            image = "1",
                            inStock = true,
                            isRefill = false,
                            price = "3,90"
                        ),
                        Product(
                            id = "20",
                            name = "Sopa de verduras",
                            image = "1",
                            inStock = true,
                            isRefill = false,
                            price = "5,10"
                        ),
                    )
                ), CategoryWithProducts(
                    categoryName = "Aperitivos", products = listOf(
                        Product(
                            id = "21",
                            name = "Patatas fritas",
                            image = "1",
                            inStock = true,
                            isRefill = false,
                            price = "3,10"
                        ),
                        Product(
                            id = "22",
                            name = "Patatas gajo",
                            image = "1",
                            inStock = true,
                            isRefill = false,
                            price = "3,10"
                        ),
                        Product(
                            id = "23",
                            name = "Nuggets de pollo",
                            image = "1",
                            inStock = false,
                            isRefill = false,
                            price = "4,10"
                        ),
                        Product(
                            id = "24",
                            name = "Tiras de pollo",
                            image = "1",
                            inStock = true,
                            isRefill = false,
                            price = "4,50"
                        ),
                        Product(
                            id = "25",
                            name = "Patatas bravas",
                            image = "1",
                            inStock = true,
                            isRefill = false,
                            price = "6,10"
                        ),
                    )
                ), CategoryWithProducts(
                    categoryName = "Postres", products = listOf(
                        Product(
                            id = "26",
                            name = "Helado de fresa",
                            image = "1",
                            inStock = true,
                            isRefill = false,
                            price = "2,90"
                        ), Product(
                            id = "27",
                            name = "Helado de chocolate",
                            image = "1",
                            inStock = true,
                            isRefill = false,
                            price = "3,10"
                        ), Product(
                            id = "28",
                            name = "Tarta de queso",
                            image = "1",
                            inStock = true,
                            isRefill = false,
                            price = "2,90"
                        ), Product(
                            id = "29",
                            name = "Flan",
                            image = "1",
                            inStock = true,
                            isRefill = false,
                            price = "2,90"
                        ), Product(
                            id = "30",
                            name = "Tiramisú",
                            image = "1",
                            inStock = true,
                            isRefill = false,
                            price = "2,90"
                        )
                    )
                )
            )
            Resource.Success(
                data = RestaurantDetails(
                    id = "1",
                    name = "Wendy's",
                    rating = 3f,
                    distance = 1,
                    categoriesWithProducts = categoriesWithProducts
                )
            )
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun getRestaurantDetailsExtra(): Resource<RestaurantDetailsExtra> {
        return try {
            Resource.Success(
                data = RestaurantDetailsExtra(
                    id = "1",
                    name = "Domino's Pizza",
                    latLng = Pair(first = 36.71429686692496, second = -4.433230427633246),
                    schedule = "Lunes: Cerrado\n" + "Martes: 12:00 a 17:00 y 20:30 a 02:00\n" + "Miércoles: 12:00 a 17:00 y 20:30 a 02:00\n" + "Jueves: 12:00 a 17:00 y 20:30 a 02:00\n" + "Viernes: 12:00 a 17:00 y 20:30 a 02:00\n" + "Sábado: 12:00 a 17:00 y 20:30 a 02:00\n" + "Domingo: 12:00 a 17:00 y 20:30 a 02:00",
                    phone = "600200300"
                )
            )
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun getRestaurantReviews(): Resource<List<RestaurantReview>> {
        return try {
            val restaurantReviews = listOf(
                RestaurantReview(
                    id = "1",
                    clientName = "Eugenio Chebotarev",
                    rating = 3f,
                    description = "El servicio en este restaurante es excelente. Los meseros son muy atentos y siempre están dispuestos a ayudar. Además, la comida es increíblemente deliciosa. Mi plato favorito es el filete con ensalada y papas fritas. ¡Definitivamente volveré!",
                    isOwnerAnswer = false,
                    date = LocalDateTime.now().minusDays(3)
                ), RestaurantReview(
                    id = "2",
                    clientName = "Diego Hermoso",
                    rating = 4f,
                    description = "¡Qué experiencia increíble! La decoración del restaurante es hermosa y el ambiente es muy agradable. La comida es increíblemente fresca y siempre se sirve caliente. Los precios son razonables y el servicio es excelente.",
                    isOwnerAnswer = false,
                    date = LocalDateTime.now().minusDays(4)
                ), RestaurantReview(
                    id = "3",
                    clientName = "David Gallardo Torres",
                    rating = 3.5f,
                    description = "Este es sin duda el mejor restaurante de la ciudad. La comida es increíblemente sabrosa y siempre se sirve caliente y fresca. Los meseros son muy amables y siempre están dispuestos a ayudar.",
                    isOwnerAnswer = false,
                    date = LocalDateTime.now().minusDays(5)
                ), RestaurantReview(
                    id = "4",
                    clientName = "Lorenzo Gómez Ríos",
                    rating = 2.5f,
                    description = "Muchas gracias por la reseña, tendremos en cuenta su punto.",
                    isOwnerAnswer = true,
                    date = LocalDateTime.now().minusDays(7)
                ), RestaurantReview(
                    id = "5",
                    clientName = "Anatoly Chebotarev",
                    rating = 4f,
                    description = "Mi esposo y yo siempre disfrutamos de nuestras visitas a este restaurante. La comida es siempre deliciosa y la atención al cliente es excelente.",
                    isOwnerAnswer = false,
                    date = LocalDateTime.now().minusDays(13)
                ), RestaurantReview(
                    id = "6",
                    clientName = "Sandra López Romarís",
                    rating = 5f,
                    description = "Este es sin duda mi restaurante favorito en la ciudad. La comida es increíblemente deliciosa y siempre se sirve caliente y fresca.",
                    isOwnerAnswer = false,
                    date = LocalDateTime.now().minusDays(15)
                ), RestaurantReview(
                    id = "7",
                    clientName = "Adrián Pintor",
                    rating = 4.5f,
                    description = "Mi familia y yo hemos estado yendo a este restaurante durante años y siempre tenemos una experiencia increíble. La comida es deliciosa y el servicio es excelente. Los meseros siempre están dispuestos a ayudar y asegurarse de que tengamos una excelente experiencia.",
                    isOwnerAnswer = false,
                    date = LocalDateTime.now().minusDays(18)
                ), RestaurantReview(
                    id = "8",
                    clientName = "Daniel Ortega",
                    rating = 1f,
                    description = "Mi esposo y yo visitamos este restaurante por primera vez la semana pasada y quedamos completamente sorprendidos por lo deliciosa que era la comida. Los meseros fueron muy amables y siempre estuvieron dispuestos a ayudar.",
                    isOwnerAnswer = false,
                    date = LocalDateTime.now().minusDays(21)
                ), RestaurantReview(
                    id = "9",
                    clientName = "Javier Mohedas",
                    rating = 2.5f,
                    description = "Este es sin duda uno de mis restaurantes favoritos en la ciudad. La comida siempre es deliciosa y el servicio es excelente.",
                    isOwnerAnswer = false,
                    date = LocalDateTime.now().minusDays(22)
                ), RestaurantReview(
                    id = "10",
                    clientName = "Anzhelika Ch",
                    rating = 3f,
                    description = "Muchas gracias por esas bonitas palabras!",
                    isOwnerAnswer = true,
                    date = LocalDateTime.now().minusDays(30)
                )
            )
            Resource.Success(
                data = restaurantReviews
            )
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }
}