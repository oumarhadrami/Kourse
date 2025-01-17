package com.kourseco.kourse.home_screens.shops_screens.cart_database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShoppingCartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cartItem: CartItem)

    @Update
    suspend fun update(cartItem: CartItem)

    @Query("delete from ShoppingCart")
    suspend fun emptyCart()

    @Delete
    suspend fun delete(cartItem: CartItem)

    @Query("select * from ShoppingCart")
    fun getAll(): LiveData<List<CartItem>>

    @Query("select COUNT(*) from ShoppingCart")
    fun getCount(): LiveData<Int>

    @Query("select * from ShoppingCart where shopItemId = :shopItemId")
    suspend fun recordExists(shopItemId : String) : List<CartItem>

    @Query("select * from ShoppingCart where shopItemId = :shopItemId")
    suspend fun getRecord(shopItemId: String) : CartItem

}