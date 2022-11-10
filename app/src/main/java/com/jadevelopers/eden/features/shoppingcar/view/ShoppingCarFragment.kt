package com.jadevelopers.eden.features.shoppingcar.view

import android.os.Bundle
import android.text.TextUtils.replace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jadevelopers.eden.R
import com.jadevelopers.eden.databinding.FragmentShoppingCarBinding
import com.jadevelopers.eden.features.base.EdenFragment
import com.jadevelopers.eden.model.ShoppingCarItem

class ShoppingCarFragment : EdenFragment() {

    private lateinit var binding: FragmentShoppingCarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentShoppingCarBinding.inflate(inflater, container, false)
        edenActivity.supportActionBar?.title =
            getString(R.string.carrito_de_compras)
        edenActivity.menu?.children?.first()?.isVisible = false
        edenActivity.shoppingCarViewModel.shoppingCarList.observe(viewLifecycleOwner) {
            recyclerShoppingCar(it)
            binding.shoppingCarContainer.isVisible = true
            binding.fallbackContainer.isVisible = false
        }
        edenActivity.shoppingCarViewModel.shoppingCarDeleteItem.observe(viewLifecycleOwner) {
            binding.shoppingCarContainer.isVisible = true
        }
        edenActivity.shoppingCarViewModel.shoppingCarError.observe(viewLifecycleOwner) {
            binding.shoppingCarContainer.isVisible = false
            binding.fallbackContainer.isVisible = true
        }
        binding.addProducts.setOnClickListener {
            this.parentFragmentManager
                .primaryNavigationFragment
                ?.findNavController()?.popBackStack()
        }

        return binding.root
    }

    private fun recyclerShoppingCar(list: ArrayList<ShoppingCarItem>) {
        val manager = LinearLayoutManager(context)
        binding.recyclerShoppingCar.layoutManager = manager
        binding.recyclerShoppingCar.adapter =
            activity?.let { ShoppingCarAdapter(list, it) }
    }

    private fun getShoppingCar() {
        activity?.applicationContext?.let {
            edenActivity.shoppingCarViewModel.getShoppingCar()
        }
    }

    override fun onResume() {
        super.onResume()
        getShoppingCar()
    }
}
