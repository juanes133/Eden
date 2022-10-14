package com.jadevelopers.eden.viewmodel

import android.content.Context
import androidx.room.Room
import com.jadevelopers.eden.ShoppingCarDb
import com.jadevelopers.eden.model.Product
import com.jadevelopers.eden.model.ShoppingCar

class ShoppingCarRepository {

    fun getShoppingCar(
        context: Context,
        onSuccess: (ArrayList<ShoppingCar>) -> Unit,
        onFailure: (Exception) -> Unit,
    ) {
        val room = Room
            .databaseBuilder(context, ShoppingCarDb::class.java, "shoppingCar")
            .build()
    }

    fun insertShoppingCarItem(
        context: Context,
        id: Int,
        amount: Int,
        onFailure: (Exception) -> Unit,
    ) {
        val room = Room
            .databaseBuilder(context, ShoppingCarDb::class.java, "shoppingCar")
            .build()
        val shoppingCar = ShoppingCar(id, amount)
        room.shoppingCarDao().insertAll(listOf(shoppingCar))
    }

}