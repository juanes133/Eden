package com.jadevelopers.eden.viewmodel

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.jadevelopers.eden.model.Product

class ProductsRepository {

    private val productsConverter = ProductsConverter()

    fun getProducts(onSuccess: (ArrayList<Product>) -> Unit, onFailure: (Exception) -> Unit) {
        val db = Firebase.firestore
        db.collection(PRODUCTS)
            .get()
            .addOnSuccessListener { result ->
                onSuccess(productsConverter.convertProducts(result))
            }
            .addOnFailureListener {
                onFailure(it)
            }
    }

    companion object {
        const val PRODUCTS = "products"
    }
}