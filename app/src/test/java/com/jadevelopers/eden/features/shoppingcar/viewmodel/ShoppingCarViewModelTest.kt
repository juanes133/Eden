package com.jadevelopers.eden.features.shoppingcar.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jadevelopers.eden.database.entities.ShoppingCar
import com.jadevelopers.eden.features.productslist.repository.ProductsRepository
import com.jadevelopers.eden.features.shoppingcar.repository.ShoppingCarRepository
import com.jadevelopers.eden.model.ShoppingCarItem
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ShoppingCarViewModelTest {

    @RelaxedMockK
    private lateinit var shoppingCarViewModel: ShoppingCarViewModel
    private lateinit var shoppingCarRepository: ShoppingCarRepository
    private lateinit var productsRepository: ProductsRepository

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        shoppingCarViewModel = ShoppingCarViewModel(shoppingCarRepository, productsRepository)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewmodel is created at the first time, get all shopping and set the first value`() =
        runTest {
            //Given
            val shoppingCarItemList = listOf(ShoppingCarItem(1, "", "", 5000, ""),
                ShoppingCarItem(2, "Otro Aris ", "", 10000, ""))
            coEvery {shoppingCarRepository} returns shoppingCarItemList

            //When
            shoppingCarViewModel.getShoppingCar()

            //Then
            assert(shoppingCarViewModel.shoppingCarInsertItem.value == true)

        }
}
