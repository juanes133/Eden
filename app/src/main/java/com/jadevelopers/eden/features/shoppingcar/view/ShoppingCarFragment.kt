package com.jadevelopers.eden.features.shoppingcar.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jadevelopers.eden.EdenActivity
import com.jadevelopers.eden.EdenApplication
import com.jadevelopers.eden.R
import com.jadevelopers.eden.databinding.FragmentShoppingCarBinding
import com.jadevelopers.eden.features.shoppingcar.viewmodel.ShoppingCarViewModel
import com.jadevelopers.eden.features.shoppingcar.viewmodel.ShoppingCarViewModelFactory
import com.jadevelopers.eden.model.ShoppingCarItem

class ShoppingCarFragment : Fragment() {

    private lateinit var binding: FragmentShoppingCarBinding
    private val shoppingCarViewModel: ShoppingCarViewModel by activityViewModels {
        ShoppingCarViewModelFactory(
            (activity?.application as EdenApplication).shoppingCarRepository,
            (activity?.application as EdenApplication).productsRepository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentShoppingCarBinding.inflate(inflater, container, false)
        (activity as EdenActivity).supportActionBar?.title =
            getString(R.string.carrito_de_compras)
        (activity as EdenActivity).menu?.children?.first()?.isVisible = false
        shoppingCarViewModel.shoppingCarItem.observe(viewLifecycleOwner) {
            recyclerShoppingCar(it)
            binding.shoppingCarContainer.isVisible = true
        }
        shoppingCarViewModel.shoppingCarError.observe(viewLifecycleOwner) {
            binding.shoppingCarContainer.isVisible = false
        }
        binding.addProducts.setOnClickListener {
            binding.addProducts.findNavController().navigate(R.id.productsFragment)
        }
        return binding.root
    }

    private fun recyclerShoppingCar(list: ArrayList<ShoppingCarItem>) {
        val manager = LinearLayoutManager(context)
        binding.recyclerShoppingCar.layoutManager = manager
        binding.recyclerShoppingCar.adapter = activity?.let { ShoppingCarAdapter(list, it) }
    }

    private fun getShoppingCar() {
        activity?.applicationContext?.let {
            shoppingCarViewModel.getShoppingCar()
        }
    }

    override fun onResume() {
        super.onResume()
        getShoppingCar()
    }
}
