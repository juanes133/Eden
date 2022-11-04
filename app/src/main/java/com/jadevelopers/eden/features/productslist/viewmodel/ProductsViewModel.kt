package com.jadevelopers.eden.features.productslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jadevelopers.eden.features.productslist.repository.ProductsRepository
import com.jadevelopers.eden.model.Product

class ProductsViewModel(private val productsRepository: ProductsRepository) : ViewModel() {

    private val mutableProductsList = MutableLiveData<ArrayList<Product>>()
    val productsList: LiveData<ArrayList<Product>> get() = mutableProductsList

    private val mutableProductsError = MutableLiveData<Exception>()
    val productsError: LiveData<Exception> get() = mutableProductsError

    fun getProducts() {
        productsRepository.getProducts({ list ->
            mutableProductsList.value = list
        }, {
            mutableProductsError.value = it
        })
    }

    class ProductsViewModelFactory(
        private val productsRepository: ProductsRepository
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ProductsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ProductsViewModel( productsRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}