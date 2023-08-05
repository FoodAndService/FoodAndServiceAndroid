package com.foodandservice.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.foodandservice.data.local.entity.RestaurantCartProductEntity
import com.foodandservice.data.local.entity.RestaurantCartProductExtraEntity
import com.foodandservice.data.local.entity.RestaurantCartProductsWithExtras

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(product: RestaurantCartProductEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductExtras(productExtras: List<RestaurantCartProductExtraEntity>)

    @Transaction
    suspend fun decrementOrDelete(cartItemId: String) {
        val currentQuantity = getProductQuantity(cartItemId)
        if (currentQuantity == 1) {
            deleteProduct(cartItemId)
        } else {
            updateQuantity(cartItemId, -1)
        }
    }

    @Query("UPDATE restaurant_cart_products SET quantity = quantity + :quantity WHERE productId = :productId")
    suspend fun updateQuantity(productId: String, quantity: Int)

    @Query("SELECT quantity FROM restaurant_cart_products WHERE productId = :productId")
    suspend fun getProductQuantity(productId: String): Int

    @Query("SELECT id FROM restaurant_cart_products WHERE productId = :productId")
    suspend fun getCartItemId(productId: String): String

    @Query("DELETE FROM restaurant_cart_products WHERE productId = :productId")
    suspend fun deleteProduct(productId: String)

    @Query("DELETE FROM restaurant_cart_product_extras WHERE cartItemId = :productId")
    suspend fun deleteProductExtras(productId: String)

    @Query("SELECT EXISTS (SELECT 1 FROM restaurant_cart_products WHERE productId = :productId LIMIT 1)")
    suspend fun productExists(productId: String): Boolean

    @Query("SELECT note FROM restaurant_cart_products WHERE productId = :productId")
    suspend fun getProductNote(productId: String): String

    @Query("DELETE FROM restaurant_cart_products")
    suspend fun emptyCart()

    @Query("SELECT * FROM restaurant_cart_product_extras WHERE cartItemId = :cartItemId")
    suspend fun getProductExtrasForCartItem(cartItemId: String): List<RestaurantCartProductExtraEntity>

    @Query("UPDATE restaurant_cart_product_extras SET quantity = :quantity WHERE productExtraId = :productExtraId AND cartItemId = :cartItemId")
    suspend fun updateProductExtraQuantity(
        productExtraId: String,
        cartItemId: String,
        quantity: Int
    )

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductExtra(extra: RestaurantCartProductExtraEntity)

    @Transaction
    @Query("SELECT * FROM restaurant_cart_products")
    suspend fun getAllProductsWithExtras(): List<RestaurantCartProductsWithExtras>
}