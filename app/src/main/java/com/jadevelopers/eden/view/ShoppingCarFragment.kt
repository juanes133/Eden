package com.jadevelopers.eden.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.jadevelopers.eden.R
import com.jadevelopers.eden.ShoppingCarDb
import com.jadevelopers.eden.adapter.ShoppingCarAdapter
import com.jadevelopers.eden.databinding.FragmentShoppingCarBinding
import com.jadevelopers.eden.model.ShoppingCar
import com.jadevelopers.eden.viewmodel.ShoppingCarViewModel
import kotlinx.coroutines.launch

class ShoppingCarFragment : Fragment() {

    private lateinit var binding: FragmentShoppingCarBinding
    private var shoppingCar: ShoppingCar? = null
    private val shoppingCarViewModel: ShoppingCarViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentShoppingCarBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.carrito_de_compras)
        shoppingCarViewModel.shoppingCarList.observe(viewLifecycleOwner) {
            recyclerShoppingCar(it)
            binding.productsShoppingContainer.isVisible = true
        }
        shoppingCarViewModel.shoppingCarError.observe(viewLifecycleOwner) {
            binding.productsShoppingContainer.isVisible = false

        }
        return binding.root
    }

    private fun recyclerShoppingCar(list: ArrayList<ShoppingCar>) {
        val manager = LinearLayoutManager(context)
        binding.recyclerShoppingCar.layoutManager = manager
        binding.recyclerShoppingCar.adapter =
            ShoppingCarAdapter(list)
    }

    private fun getShoppingCar() {
        activity?.applicationContext?.let {
            shoppingCarViewModel.getShoppingCar(it)
        }
    }

    override fun onResume() {
        super.onResume()
        getShoppingCar()
    }
}