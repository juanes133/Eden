package com.jadevelopers.eden.features.productslist.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.jadevelopers.eden.database.entities.ShoppingCar
import com.jadevelopers.eden.model.Product

class ProductsRepository(private val firebaseDataBase: FirebaseFirestore) {

    private val productsConverter = ProductsConverter()

    fun getProducts(onSuccess: (ArrayList<Product>) -> Unit, onFailure: (Exception) -> Unit) {
        if (cacheProducts.isNotEmpty()) {
            onSuccess(cacheProducts)
        }

        firebaseDataBase.collection(PRODUCTS)
            .get()
            .addOnSuccessListener { result ->
                cacheProducts = productsConverter.convertProducts(result)
                onSuccess(cacheProducts)
            }
            .addOnFailureListener {
                onFailure(it)
            }
    }

    fun getProductsByIds(list: ArrayList<ShoppingCar>) {
        val ids = Product("1", "","blueberry","","","","10000","")
        val filtered = ids.id.filter {}
        filtered.toSet()
    }

    companion object {
        const val PRODUCTS = "products"
        private var cacheProducts = ArrayList<Product>()
    }
}