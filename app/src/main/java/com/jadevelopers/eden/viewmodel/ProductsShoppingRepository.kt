package com.jadevelopers.eden.viewmodel

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.jadevelopers.eden.model.ProductsShopping

class ProductsShoppingRepository {
    private val productsShoppingConverter = ProductsShoppingConverter()

    fun getProductsShopping(onSuccess: (ArrayList<ProductsShopping>) -> Unit, onFailure: (Exception) -> Unit) {
        if(cacheProductsShopping.isNotEmpty()){
            onSuccess(cacheProductsShopping)
        }
        val db = Firebase.firestore
        db.collection(PRODUCTS)
            .get()
            .addOnSuccessListener { result ->
                cacheProductsShopping = productsShoppingConverter.convertProductsShopping(result)
                onSuccess(cacheProductsShopping)
            }
            .addOnFailureListener {
                onFailure(it)
            }
    }

    companion object {
        const val PRODUCTS = "products"
        private var cacheProductsShopping = ArrayList<ProductsShopping>()
    }
}