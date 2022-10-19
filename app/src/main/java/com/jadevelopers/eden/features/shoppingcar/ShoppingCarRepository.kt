package com.jadevelopers.eden.features.shoppingcar

import android.content.Context
import androidx.room.Room
import com.jadevelopers.eden.database.ShoppingCarDb
import com.jadevelopers.eden.database.entities.ShoppingCar

class ShoppingCarRepository {
    fun getShoppingCar(
        context: Context,
        onSuccess: (ArrayList<ShoppingCar>) -> Unit,
        onFailure: (Exception) -> Unit,
    ) {
        try {
            onSuccess(
                Room.databaseBuilder(context, ShoppingCarDb::class.java, SHOPPINGCAR)
                    .build().shoppingCarDao().getAll() as ArrayList<ShoppingCar>
            )
        } catch (e: Exception) {
            onFailure(e)
        }
    }

    fun insertShoppingCarItem(
        context: Context,
        id: Int,
        amount: Int,
        onFailure: (Exception) -> Unit,
    ) {
        try {
            val room = Room
                .databaseBuilder(context, ShoppingCarDb::class.java, "shoppingCar")
                .build()
            val shoppingCar = ShoppingCar(id, amount)
            room.shoppingCarDao().insertAll(listOf(shoppingCar))
        } catch (e: Exception) {
            onFailure(e)
        }
    }

    companion object {
        const val SHOPPINGCAR = "shoppingCar"

    }
}
