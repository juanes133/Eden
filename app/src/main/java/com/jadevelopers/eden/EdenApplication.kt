package com.jadevelopers.eden

import android.app.Application
import com.jadevelopers.eden.database.ShoppingCarDb
import com.jadevelopers.eden.features.shoppingcar.repository.ShoppingCarRepository

class EdenApplication : Application() {
    val database by lazy { ShoppingCarDb.getDatabase(this) }
    val shoppingCarRepository by lazy { ShoppingCarRepository(database.shoppingCarDao()) }
}