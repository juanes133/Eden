package com.jadevelopers.eden.features.productslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jadevelopers.eden.features.productslist.repository.ProductsRepository
import com.jadevelopers.eden.model.Product

class ProductsViewModel : ViewModel() {

    private val mutableProductsList = MutableLiveData<ArrayList<Product>>()
    val productsList: LiveData<ArrayList<Product>> get() = mutableProductsList

    private val mutableProductsError = MutableLiveData<Exception>()
    val productsError: LiveData<Exception> get() = mutableProductsError

    private val productsRepository = ProductsRepository()

    fun getProducts() {
        productsRepository.getProducts({ list ->
            mutableProductsList.value = list
        }, {
            mutableProductsError.value = it
        })
    }
}