package com.jadevelopers.eden.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jadevelopers.eden.model.Product

class ProductsViewModel : ViewModel() {

    private val mutableProductsList = MutableLiveData<ArrayList<Product>>()
    val productsList: LiveData<ArrayList<Product>> get() = mutableProductsList

    fun getProducts() {
        val list = ArrayList<Product>()
        list.add(Product("1", "Descripcion", "Yuber", "jugoso", "potente", "35%", "2000", ""))
        mutableProductsList.value = list
    }
}