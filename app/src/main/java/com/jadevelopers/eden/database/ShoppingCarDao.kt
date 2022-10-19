package com.jadevelopers.eden.database

import androidx.room.*
import com.jadevelopers.eden.database.entities.ShoppingCar

@Dao
interface ShoppingCarDao {

    @Query("SELECT * FROM shoppingCar ")
    fun getAll(): List<ShoppingCar>

    @Query("SELECT * FROM shoppingCar WHERE id = :id")
    fun getById(id: Int): List<ShoppingCar>

    @Update
    fun update(ShoppingCar: ShoppingCar)

    @Insert
    fun insertAll(shoppingCar: List<ShoppingCar>)

    @Delete
    fun delete(ShoppingCar: ShoppingCar)
}