package com.jadevelopers.eden.view

import android.R.color
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jadevelopers.eden.R
import com.jadevelopers.eden.adapter.CannabisAdapter
import com.jadevelopers.eden.databinding.FragmentProductsBinding
import com.jadevelopers.eden.model.Product
import com.jadevelopers.eden.viewmodel.ProductsViewModel


class ProductsFragment : Fragment() {

    private lateinit var binding: FragmentProductsBinding
    private val productsViewModel: ProductsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentProductsBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.titulo_productos)
        (activity as AppCompatActivity).supportActionBar?.show()
        productsViewModel.productsList.observe(viewLifecycleOwner) {
            initRecyclerView(it)
            binding.loading.isVisible = false
            binding.productsContainer.isVisible = true
        }
        productsViewModel.productsError.observe(viewLifecycleOwner){
            binding.loading.isVisible = false
            binding.fallbackContainer.isVisible = true
            binding.productsContainer.isVisible = false
        }
        getProducts()
        binding.btnRetry.setOnClickListener {
            getProducts()
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            binding.loading.indeterminateDrawable.colorFilter =
                BlendModeColorFilter(Color.GREEN, BlendMode.SRC_ATOP)
        } else {
            @Suppress("DEPRECATION")
            binding.loading.indeterminateDrawable.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        return binding.root
    }

    private fun getProducts() {
        binding.fallbackContainer.isVisible = false
        binding.loading.isVisible = true
        productsViewModel.getProducts()
    }

    private fun initRecyclerView(list: ArrayList<Product>) {
        val manager = LinearLayoutManager(context)
        val decoration = DividerItemDecoration(context, manager.orientation)
        binding.recyclerCannabis.layoutManager = manager
        binding.recyclerCannabis.adapter =
            CannabisAdapter(list) { cannabis -> onItemSelect(cannabis) }
        binding.recyclerCannabis.addItemDecoration(decoration)
    }

    private fun onItemSelect(product: Product) {
        findNavController().navigate(ProductsFragmentDirections.actionProductsFragmentToDescriptionFragment(product.id))
    }

    override fun onStart() {
        super.onStart()
        if (Firebase.auth.currentUser == null) {
            findNavController().navigate(R.id.action_productsFragment_to_loginFragment)
        }
    }
}




