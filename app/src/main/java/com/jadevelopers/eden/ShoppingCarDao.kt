package com.jadevelopers.eden

import androidx.room.*
import com.jadevelopers.eden.model.ShoppingCar

@Dao
interface ShoppingCarDao {

    @Query("SELECT * FROM shoppingCar")
    fun getAll(): List<ShoppingCar>

    @Query("SELECT * FROM shoppingCar WHERE id = :id")
    fun getById(id: String): List<ShoppingCar>

    @Update
    fun update(ShoppingCar: ShoppingCar)

    @Insert
    fun insertAll(product: List<ShoppingCar>)

    @Delete
    fun delete(ShoppingCar: ShoppingCar)
}