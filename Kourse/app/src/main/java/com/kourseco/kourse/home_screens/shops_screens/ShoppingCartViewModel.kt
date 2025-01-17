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
    val allItemsCount : LiveData<Int>

    init {
        val dao = ShoppingCartDatabase.getDatabase(application).shoppingCartDao
        repo = ShoppingCartRepository(dao)
        allCartItems =repo.allCartItems
        allItemsCount = repo.alltemsCount

    }

    suspend fun insert(cartItem: CartItem) = viewModelScope.launch {
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

    suspend fun emptyCart() = viewModelScope.launch {
        repo.emptyCart()
    }

    suspend fun deleteCartItem(cartItem : CartItem) = viewModelScope.launch {
        repo.deleteCartItem(cartItem)
    }

    suspend fun update(cartItem: CartItem) = viewModelScope.launch {
        repo.update(cartItem)
    }

//    suspend fun getAllCartItemsSize() : LiveData<List<CartItem>> {
//        val allCartItems = viewModelScope.async {
//             repo.getAllCartItems()
//        }
//        return allCartItems.await()
//    }
}