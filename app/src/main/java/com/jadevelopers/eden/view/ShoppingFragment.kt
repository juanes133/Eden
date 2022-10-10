package com.jadevelopers.eden.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jadevelopers.eden.R
import com.jadevelopers.eden.adapter.ProductsShoppingAdapter
import com.jadevelopers.eden.databinding.FragmentShoppingBinding
import com.jadevelopers.eden.model.ProductsShopping
import com.jadevelopers.eden.viewmodel.ShoppingViewModel

class ShoppingFragment() : Fragment() {

    private lateinit var binding: FragmentShoppingBinding
    private var product: ProductsShopping? = null
    private val shoppingViewModel: ShoppingViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentShoppingBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.compra)
        shoppingViewModel.productsShoppingList.observe(viewLifecycleOwner) {
            recyclerProductsShopping(it)
            binding.productsShoppingContainer.isVisible = true
        }
        getProductsShopping()
        shoppingViewModel.productsShoppingError.observe(viewLifecycleOwner) {

        }

        return binding.root

    }

    private fun recyclerProductsShopping(list: ArrayList<ProductsShopping>) {
        val manager = LinearLayoutManager(context)
        binding.recyclerProductsShopping.layoutManager = manager
        binding.recyclerProductsShopping.adapter =
            ProductsShoppingAdapter(list)
    }

    private fun onItemSelect(productsShopping: ProductsShopping) {
        findNavController().navigate(
            ProductsFragmentDirections.actionProductsFragmentToDescriptionFragment(
                productsShopping.namePlant
            )
        )
    }
    private fun getProductsShopping() {
        shoppingViewModel.getProductsShopping()
    }
}