package com.jadevelopers.eden.network

import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.jadevelopers.eden.databinding.FragmentProductsBinding
import com.jadevelopers.eden.model.Product

class Repo {
    private lateinit var binding: FragmentProductsBinding

 fun dataBase(): LiveData<MutableList<Product>> {
        binding.fallbackContainer.isVisible = false
        binding.loading.isVisible = true
        val db = Firebase.firestore
        val mutableData = MutableLiveData<MutableList<Product>>()
        db.collection("products")
            .get()
            .addOnSuccessListener { result ->
                val list = mutableListOf<Product>()
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
                            document.data["imagen"].toString()

                        )
                    )
                }
                mutableData.value = list
                binding.loading.isVisible = false
                binding.productsContainer.isVisible = true
            }
            .addOnFailureListener {
                binding.loading.isVisible = false
                binding.fallbackContainer.isVisible = true
                binding.productsContainer.isVisible = false
            }
     return mutableData
    }
}


