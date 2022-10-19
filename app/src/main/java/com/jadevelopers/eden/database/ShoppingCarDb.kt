package com.jadevelopers.eden.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jadevelopers.eden.database.entities.ShoppingCar

@Database(entities = [ShoppingCar::class], version = 1)

abstract class ShoppingCarDb : RoomDatabase() {
    abstract fun shoppingCarDao(): ShoppingCarDao
}
