package com.jadevelopers.eden.features.productslist.repository

import com.google.firebase.firestore.QuerySnapshot
import com.jadevelopers.eden.model.Product

class ProductsConverter {

    fun convertProducts(result: QuerySnapshot):ArrayList<Product> {
        val list = ArrayList<Product>()
        for (document in result) {
            list.add(
                Product(
                    document.id,
                    document.data["descripcion"].toString(),
                    document.data["nombrePlanta"].toString(),
                    document.data["sabor"].toString(),
                    document.data["efecto"].toString(),
                    document.data["thc"].toString(),
                    document.data["precio"].toString(),
                    document.data["imagen"].toString(),
                )
            )
        }
        return list
    }
}