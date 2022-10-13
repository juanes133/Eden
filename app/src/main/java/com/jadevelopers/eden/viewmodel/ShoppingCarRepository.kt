package com.jadevelopers.eden.viewmodel

import android.content.Context
import androidx.room.Room
import com.jadevelopers.eden.ShoppingCarDb
import com.jadevelopers.eden.model.ShoppingCar

class ShoppingCarRepository {
    fun getShoppingCar(
        context: Context,
        onSuccess: (ArrayList<ShoppingCar>) -> Unit,
        onFailure: (Exception) -> Unit
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

    companion object {
        const val SHOPPINGCAR = "shoppingCar"

    }
}

