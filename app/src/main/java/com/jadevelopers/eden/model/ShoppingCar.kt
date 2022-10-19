package com.jadevelopers.eden.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shoppingCar")
data class ShoppingCar(
    @PrimaryKey
    val id: Int,
    val amount:Int
)


