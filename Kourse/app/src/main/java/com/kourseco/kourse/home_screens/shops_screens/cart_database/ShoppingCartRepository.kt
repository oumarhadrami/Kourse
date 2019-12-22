package com.kourseco.kourse.home_screens.shops_screens.cart_database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class ShoppingCartRepository(private val shoppingCartDao: ShoppingCartDao) {

    val allCartItems : LiveData<List<CartItem>> = shoppingCartDao.getAll()

    @WorkerThread
    suspend fun insert(cartItem: CartItem){
        shoppingCartDao.insert(cartItem)
    }

    @WorkerThread
    suspend fun update(cartItem: CartItem){
        shoppingCartDao.update(cartItem)
    }

    @WorkerThread
    suspend fun emptyCart(){
        shoppingCartDao.emptyCart()
    }

    @WorkerThread
    suspend fun deleteCartItem(cartItem: CartItem){
        shoppingCartDao.delete(cartItem)
    }
    @WorkerThread
    suspend fun recordExists(shopItemId : String) : Int{
        val itemsWithThisId : List<CartItem> = shoppingCartDao.recordExists(shopItemId)
        return itemsWithThisId.size
    }

    @WorkerThread
    suspend fun getRecord(shopItemId : String) : CartItem{
        val cartItemWithThisId : CartItem = shoppingCartDao.getRecord(shopItemId)
        return cartItemWithThisId
    }

}