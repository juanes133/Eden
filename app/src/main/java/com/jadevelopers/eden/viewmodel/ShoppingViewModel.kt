package com.jadevelopers.eden.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jadevelopers.eden.model.ProductsShopping

class ShoppingViewModel : ViewModel() {
    private val mutableProductsShoppingList = MutableLiveData<ArrayList<ProductsShopping>>()
    val productsShoppingList: LiveData<ArrayList<ProductsShopping>> get() = mutableProductsShoppingList

    private val mutableProductsShoppingError = MutableLiveData<Exception>()
    val productsShoppingError: LiveData<Exception> get() = mutableProductsShoppingError

    private val productsShoppingRepository = ProductsShoppingRepository()

    fun getProductsShopping() {
        productsShoppingRepository.getProductsShopping({ list ->
            mutableProductsShoppingList.value = list
        }, {
            mutableProductsShoppingError.value = it
        })
    }
}
