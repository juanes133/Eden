package com.jadevelopers.eden.features.shoppingcar.viewmodel

import androidx.lifecycle.*
import com.jadevelopers.eden.features.productslist.repository.ProductsRepository
import com.jadevelopers.eden.features.shoppingcar.repository.ShoppingCarRepository
import com.jadevelopers.eden.model.ShoppingCarItem
import kotlinx.coroutines.launch

class ShoppingCarViewModel(
    private val shoppingCarRepository: ShoppingCarRepository,
    private val productsRepository: ProductsRepository,
) : ViewModel() {

    private val mutableShoppingCarItem = MutableLiveData<ArrayList<ShoppingCarItem>>()
    val shoppingCarItem: LiveData<ArrayList<ShoppingCarItem>> get() = mutableShoppingCarItem

    private val mutableShoppingCarItemError = MutableLiveData<Exception>()
    val shoppingCarError: LiveData<Exception> get() = mutableShoppingCarItemError

    fun getShoppingCar() {
        viewModelScope.launch {
            shoppingCarRepository.getShoppingCar({ list ->
                val products = productsRepository.getProductsByIds(list)
                val result = ArrayList<ShoppingCarItem>()
                list.forEach {
                    val product = products.first { x -> x.id == it.id.toString() }
                    result.add(ShoppingCarItem(it.id, product.namePlant, product.photo, it.amount, product.price))
                }
                mutableShoppingCarItem.value = result
            }, {
                mutableShoppingCarItemError.value = it
            })
        }
    }

    fun insertShoppingCarItem(id: Int, amount: Int) {
        viewModelScope.launch {
            shoppingCarRepository.insertShoppingCarItem(id, amount) {
                mutableShoppingCarItemError.value = it
            }
        }
    }
}

class ShoppingCarViewModelFactory(
    private val shoppingCarRepository: ShoppingCarRepository,
    private val productsRepository: ProductsRepository,
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShoppingCarViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ShoppingCarViewModel(shoppingCarRepository, productsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
