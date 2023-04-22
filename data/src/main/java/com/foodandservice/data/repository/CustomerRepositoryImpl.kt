package com.foodandservice.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.foodandservice.data.remote.datasource.CustomerRemoteDataSource
import com.foodandservice.data.remote.model.restaurant.toRestaurant
import com.foodandservice.data.remote.model.restaurant.toRestaurantCategory
import com.foodandservice.data.remote.model.restaurant_details.toRestaurantDetails
import com.foodandservice.data.remote.model.restaurant_details.toRestaurantProductCategory
import com.foodandservice.data.remote.model.restaurant_details.toRestaurantProductDetails
import com.foodandservice.data.remote.model.restaurant_details.toRestaurantProductPrice
import com.foodandservice.data.remote.service.CustomerService
import com.foodandservice.domain.model.AllergenIntolerance
import com.foodandservice.domain.model.Booking
import com.foodandservice.domain.model.CartItem
import com.foodandservice.domain.model.FavouriteRestaurant
import com.foodandservice.domain.model.Order
import com.foodandservice.domain.model.OrderProduct
import com.foodandservice.domain.model.ProductDetails
import com.foodandservice.domain.model.ProductExtra
import com.foodandservice.domain.model.RestaurantReview
import com.foodandservice.domain.model.location.Coordinate
import com.foodandservice.domain.model.restaurant.Restaurant
import com.foodandservice.domain.model.restaurant.RestaurantCategory
import com.foodandservice.domain.model.restaurant_details.RestaurantDetails
import com.foodandservice.domain.model.restaurant_details.RestaurantProductCategoryWithProducts
import com.foodandservice.domain.model.restaurant_details.RestaurantProductDetails
import com.foodandservice.domain.repository.CustomerRepository
import com.foodandservice.domain.util.ApiResponse
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime

class CustomerRepositoryImpl(
    private val customerRemoteDataSource: CustomerRemoteDataSource,
    private val customerService: CustomerService
) : CustomerRepository {

    override fun getRestaurants(
        coordinate: Coordinate
    ): Flow<PagingData<Restaurant>> {
        return customerRemoteDataSource.getRestaurants(coordinate)
            .map { pagingData -> pagingData.map { restaurantDto -> restaurantDto.toRestaurant() } }
    }

    override suspend fun getRestaurantCategories(): ApiResponse<List<RestaurantCategory>> {
        return try {
            val categories = customerService.getRestaurantCategories()
            ApiResponse.Success(data = categories.map { it.toRestaurantCategory() })
        } catch (exception: Exception) {
            ApiResponse.Failure(exception)
        }
    }

    override suspend fun getRestaurantDetails(restaurantId: String): ApiResponse<RestaurantDetails> {
        return try {
            val restaurantDetails = customerService.getRestaurantDetails(restaurantId)
            ApiResponse.Success(data = restaurantDetails.toRestaurantDetails())
        } catch (exception: Exception) {
            ApiResponse.Failure(exception)
        }
    }

    override suspend fun getRestaurantProductCategoriesWithProducts(restaurantId: String): ApiResponse<List<RestaurantProductCategoryWithProducts>> =
        coroutineScope {
            return@coroutineScope try {
                val restaurantProductCategories =
                    async { customerService.getRestaurantProductCategories(restaurantId) }
                val restaurantProducts =
                    async { customerService.getRestaurantProducts(restaurantId) }
                val restaurantProductCategoriesResult =
                    restaurantProductCategories.await().map { it.toRestaurantProductCategory() }
                        .sortedBy { it.order }
                val restaurantProductsResult =
                    restaurantProducts.await().map { it.toRestaurantProductPrice() }
                val restaurantProductCategoryWithProducts =
                    mutableListOf<RestaurantProductCategoryWithProducts>()

                restaurantProductCategoriesResult.map { productCategory ->
                    val filteredProducts =
                        restaurantProductsResult.filter { product -> product.categoryId == productCategory.id }

                    restaurantProductCategoryWithProducts.add(
                        RestaurantProductCategoryWithProducts(
                            category = productCategory.name, products = filteredProducts
                        )
                    )
                }

                ApiResponse.Success(data = restaurantProductCategoryWithProducts)
            } catch (exception: Exception) {
                ApiResponse.Failure(exception)
            }
        }

    override suspend fun getRestaurantProductDetails(
        restaurantId: String, productId: String
    ): ApiResponse<RestaurantProductDetails> {
        return try {
            val restaurantProductDetails = customerService.getRestaurantProductDetails(
                restaurantId = restaurantId, productId = productId
            ).toRestaurantProductDetails()
            ApiResponse.Success(data = restaurantProductDetails)
        } catch (exception: Exception) {
            ApiResponse.Failure(exception)
        }
    }

    override suspend fun getFavouriteRestaurants(): ApiResponse<List<FavouriteRestaurant>> {
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
            ApiResponse.Success(data = favouriteRestaurants)
        } catch (exception: Exception) {
            ApiResponse.Failure(exception)
        }
    }

    override suspend fun getOrderHistory(): ApiResponse<List<Order>> {
        return try {
            val orderHistory = listOf(
                Order(
                    id = "1",
                    restaurantName = "Wendy's",
                    price = "15,00",
                    date = LocalDateTime.of(2023, 2, 18, 0, 0)
                ), Order(
                    id = "2",
                    restaurantName = "Rosario's Burger",
                    price = "12,00",
                    date = LocalDateTime.of(2022, 11, 14, 0, 0)
                ), Order(
                    id = "3",
                    restaurantName = "Foster Hollywood",
                    price = "30,00",
                    date = LocalDateTime.of(2022, 11, 11, 0, 0)
                ), Order(
                    id = "4",
                    restaurantName = "La Calle Burger",
                    price = "17,00",
                    date = LocalDateTime.of(2022, 11, 7, 0, 0)
                ), Order(
                    id = "5",
                    restaurantName = "Kalua",
                    price = "6,00",
                    date = LocalDateTime.of(2022, 10, 3, 0, 0)
                )
            )
            ApiResponse.Success(data = orderHistory)
        } catch (exception: Exception) {
            ApiResponse.Failure(exception)
        }
    }

    override suspend fun getBookings(): ApiResponse<List<Booking>> {
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
            ApiResponse.Success(data = bookings)
        } catch (exception: Exception) {
            ApiResponse.Failure(exception)
        }
    }

    override suspend fun getCart(): ApiResponse<List<CartItem>> {
        return try {
            val cartItems = listOf(
                CartItem("1", "Pepsi", "", "1,99", 1, false),
                CartItem("2", "Copa de vino", "", "2,99", 1, false),
                CartItem("3", "Patatas fritas", "", "0,99", 1, true),
                CartItem("4", "Pollo frito", "", "3,99", 1, false),
                CartItem("5", "Patatas fritas", "", "0,99", 1, true),
                CartItem("6", "Patatas gajo", "", "0,99", 1, true),
                CartItem("7", "Pepsi", "", "1,99", 1, false),
                CartItem("8", "Copa de vino", "", "2,99", 1, false),
                CartItem("9", "Patatas fritas", "", "0,99", 1, true),
                CartItem("10", "Pollo frito", "", "3,99", 1, false),
                CartItem("11", "Patatas fritas", "", "0,99", 1, true),
                CartItem("12", "Patatas gajo", "", "0,99", 1, true)
            )
            ApiResponse.Success(data = cartItems)
        } catch (exception: Exception) {
            ApiResponse.Failure(exception)
        }
    }

    override suspend fun getOrderProducts(): ApiResponse<List<OrderProduct>> {
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
            ApiResponse.Success(data = orderProducts)
        } catch (exception: Exception) {
            ApiResponse.Failure(exception)
        }
    }

    override suspend fun getOrderStatus(): ApiResponse<String> {
        return try {
            val orderStatus = listOf(
                "CHECKING_PAYMENT",
                "FINISHED",
                "ORDER_DECLINED",
                "PAYMENT DECLINED",
                "PREPARING",
                "WAITING_CONFIRMATION"
            )
            ApiResponse.Success(data = orderStatus.random())
        } catch (exception: Exception) {
            ApiResponse.Failure(exception)
        }
    }

    override suspend fun getProductDetails(): ApiResponse<ProductDetails> {
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
            ApiResponse.Success(
                data = ProductDetails(
                    id = "1",
                    allergensAndIntolerances = allergensAndIntolerances,
                    productExtras = productExtras
                )
            )
        } catch (exception: Exception) {
            ApiResponse.Failure(exception)
        }
    }

    override suspend fun getRestaurantReviews(): ApiResponse<List<RestaurantReview>> {
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
            ApiResponse.Success(
                data = restaurantReviews
            )
        } catch (exception: Exception) {
            ApiResponse.Failure(exception)
        }
    }
}