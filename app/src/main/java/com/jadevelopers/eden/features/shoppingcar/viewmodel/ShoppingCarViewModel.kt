package com.jadevelopers.eden.features.shoppingcar.viewmodel

import androidx.lifecycle.*
import com.jadevelopers.eden.database.entities.ShoppingCar
import com.jadevelopers.eden.features.productslist.repository.ProductsRepository
import com.jadevelopers.eden.features.shoppingcar.repository.ShoppingCarRepository
import kotlinx.coroutines.launch

class ShoppingCarViewModel(
    private val shoppingCarRepository: ShoppingCarRepository,
    private val productsRepository: ProductsRepository
) : ViewModel() {
    private val mutableShoppingCarList = MutableLiveData<ArrayList<ShoppingCar>>()
    val shoppingCarList: LiveData<ArrayList<ShoppingCar>> get() = mutableShoppingCarList

    private val mutableShoppingCarError = MutableLiveData<Exception>()
    val shoppingCarError: LiveData<Exception> get() = mutableShoppingCarError

    fun getShoppingCar() {
        viewModelScope.launch {
            shoppingCarRepository.getShoppingCar({ list ->
                productsRepository.getProductsByIds(list)
            }, {
                mutableShoppingCarError.value = it
            })
        }
    }

    fun insertShoppingCarItem(id: Int, amount: Int) {
        viewModelScope.launch {
            shoppingCarRepository.insertShoppingCarItem(id, amount) {
                mutableShoppingCarError.value = it
            }
        }
    }

}

class ShoppingCarViewModelFactory(
    private val shoppingCarRepository: ShoppingCarRepository,
    private val productsRepository: ProductsRepository
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
