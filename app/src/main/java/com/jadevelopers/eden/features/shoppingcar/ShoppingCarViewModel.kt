package com.jadevelopers.eden.features.shoppingcar

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jadevelopers.eden.database.entities.ShoppingCar
import com.jadevelopers.eden.features.shoppingcar.ShoppingCarRepository

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

    fun insertShoppingCarItem(context: Context, id: Int, amount: Int) {
        shoppingCarRepository.insertShoppingCarItem(context,id,amount) {
            mutableShoppingCarError.value = it
        }
    }

}
