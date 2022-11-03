package com.jadevelopers.eden.database

import androidx.room.*
import com.jadevelopers.eden.database.entities.ShoppingCar

@Dao
interface ShoppingCarDao {

    @Query("SELECT * FROM shoppingCar ")
    fun getAll(): List<ShoppingCar>

    @Query("SELECT * FROM shoppingCar WHERE id = :id")
    suspend fun getById(id: Int): List<ShoppingCar>

    @Update
    suspend fun update(ShoppingCar: ShoppingCar)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(shoppingCar: ShoppingCar)

    @Query("DELETE FROM shoppingCar WHERE id = :id")
    suspend fun delete(id: Int)
}