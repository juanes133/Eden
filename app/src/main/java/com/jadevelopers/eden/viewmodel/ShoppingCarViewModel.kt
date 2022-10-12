package com.jadevelopers.eden.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jadevelopers.eden.model.ShoppingCar

class ShoppingCarViewModel : ViewModel() {
    private val mutableShoppingCarList = MutableLiveData<ArrayList<ShoppingCar>>()
    val shoppingCarList: LiveData<ArrayList<ShoppingCar>> get() = mutableShoppingCarList

    private val mutableShoppingCarError = MutableLiveData<Exception>()
    val shoppingCarError: LiveData<Exception> get() = mutableShoppingCarError

    private val shoppingCarRepository = ShoppingCarRepository()


    fun getShoppingCar(context: Context) {
        shoppingCarRepository.getShoppingCar(context, { list ->
            mutableShoppingCarList.value = list
        }, {
            mutableShoppingCarError.value = it
        })
    }

    
}
