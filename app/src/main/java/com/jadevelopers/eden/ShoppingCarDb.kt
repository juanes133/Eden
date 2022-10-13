package com.jadevelopers.eden

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jadevelopers.eden.model.ShoppingCar

@Database(entities = [ShoppingCar::class], version = 1)

abstract class ShoppingCarDb : RoomDatabase() {
    abstract fun shoppingCarDao(): ShoppingCarDao
}
