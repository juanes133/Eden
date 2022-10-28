package com.jadevelopers.eden.features.shoppingcar.repository

import com.jadevelopers.eden.database.ShoppingCarDao
import com.jadevelopers.eden.database.entities.ShoppingCar
import com.jadevelopers.eden.model.Product

class ShoppingCarRepository(private val shoppingCarDao: ShoppingCarDao) {

    fun getShoppingCar(
        onSuccess: (ArrayList<ShoppingCar>) -> Unit,
        onFailure: (Exception) -> Unit,
    ) {
        try {
            onSuccess(
                shoppingCarDao.getAll() as ArrayList<ShoppingCar>
            )
        } catch (e: Exception) {
            onFailure(e)
        }
    }

    suspend fun insertShoppingCarItem(
        id: Int,
        amount: Int,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit,
    ) {
        try {
            val shoppingCar = ShoppingCar(id, amount)
            shoppingCarDao.insert(shoppingCar)
            onSuccess()
        } catch (e: Exception) {
            onFailure(e)
        }
    }

    suspend fun deleteShoppingCarItem(
        id: Int,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit,
    ) {
        try {
            shoppingCarDao.delete(id)
            onSuccess()
        } catch (e: Exception) {
            onFailure(e)
        }
    }
}
