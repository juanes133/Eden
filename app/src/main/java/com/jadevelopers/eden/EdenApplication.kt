package com.jadevelopers.eden

import android.app.Application
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.jadevelopers.eden.database.ShoppingCarDb
import com.jadevelopers.eden.features.productslist.repository.ProductsRepository
import com.jadevelopers.eden.features.shoppingcar.repository.ShoppingCarRepository

class EdenApplication : Application() {
    private val roomDataBase by lazy { ShoppingCarDb.getDatabase(this) }
    val shoppingCarRepository by lazy { ShoppingCarRepository(roomDataBase.shoppingCarDao()) }
    private val firebaseDataBase by lazy { Firebase.firestore }
    val productsRepository by lazy { ProductsRepository(firebaseDataBase) }
}