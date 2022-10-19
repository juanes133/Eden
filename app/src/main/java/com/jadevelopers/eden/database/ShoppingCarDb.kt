package com.jadevelopers.eden.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jadevelopers.eden.database.entities.ShoppingCar

@Database(entities = [ShoppingCar::class], version = 1)

abstract class ShoppingCarDb : RoomDatabase() {
    abstract fun shoppingCarDao(): ShoppingCarDao

    companion object {
        @Volatile
        private var INSTANCE: ShoppingCarDb? = null

        fun getDatabase(context: Context): ShoppingCarDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShoppingCarDb::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
