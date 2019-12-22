package com.kourseco.kourse.home_screens.shops_screens

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kourseco.kourse.home_screens.shops_screens.cart_database.CartItem
import com.kourseco.kourse.home_screens.shops_screens.cart_database.ShoppingCartDatabase
import com.kourseco.kourse.home_screens.shops_screens.cart_database.ShoppingCartRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ShoppingCartViewModel(application: Application): ViewModel()  {
    private val repo : ShoppingCartRepository
    val allCartItems : LiveData<List<CartItem>>

    init {
        val dao = ShoppingCartDatabase.getDatabase(application).shoppingCartDao
        repo = ShoppingCartRepository(dao)
        allCartItems = repo.allCartItems
    }

    fun insert(cartItem: CartItem) = viewModelScope.launch {
        repo.insert(cartItem)
    }

    suspend fun recordExists(shopItemId : String) : Boolean{
        val itemsWithThisId = viewModelScope.async{
            repo.recordExists(shopItemId)
        }
        Log.i("kotQ",""+itemsWithThisId.await())
        return itemsWithThisId.await() >= 1
    }

    suspend fun getRecord(shopItemId: String) : CartItem{
        val cartItemWithThisId = viewModelScope.async {
            repo.getRecord(shopItemId)
        }
        return cartItemWithThisId.await()
    }

    fun emptyCart() = viewModelScope.launch {
        repo.emptyCart()
    }

    fun deleteCartItem(cartItem : CartItem) = viewModelScope.launch {
        repo.deleteCartItem(cartItem)
    }

    fun update(cartItem: CartItem) = viewModelScope.launch {
        repo.update(cartItem)
    }
}