package com.jadevelopers.eden.viewmodel

import com.google.firebase.firestore.QuerySnapshot
import com.jadevelopers.eden.model.ProductsShopping

class ProductsShoppingConverter {

    fun convertProductsShopping(result: QuerySnapshot): ArrayList<ProductsShopping> {
        val list = ArrayList<ProductsShopping>()
        for (document in result) {
            list.add(
                ProductsShopping(
                    document.id,
                    document.data["nombrePlanta"].toString(),
                    document.data["imagen"].toString(),
                    document.data["precio"].toString()
                )
            )
        }
        return list
    }
}