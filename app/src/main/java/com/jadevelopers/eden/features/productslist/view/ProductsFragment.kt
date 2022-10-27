package com.jadevelopers.eden.features.productslist.view

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jadevelopers.eden.EdenApplication
import com.jadevelopers.eden.R
import com.jadevelopers.eden.databinding.FragmentProductsBinding
import com.jadevelopers.eden.features.productslist.viewmodel.ProductsViewModel
import com.jadevelopers.eden.features.productslist.viewmodel.ProductsViewModel.ProductsViewModelFactory
import com.jadevelopers.eden.model.Product

class ProductsFragment : Fragment() {

    private lateinit var binding: FragmentProductsBinding
    private val productsViewModel: ProductsViewModel by activityViewModels {
        ProductsViewModelFactory(
            (activity?.application as EdenApplication).productsRepository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        activity?.window?.clearFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding = FragmentProductsBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.titulo_productos)
        (activity as AppCompatActivity).supportActionBar?.show()
        productsViewModel.productsList.observe(viewLifecycleOwner) {
            initRecyclerViewProduct(it)
            binding.loading.isVisible = false
            binding.productsContainer.isVisible = true
            binding.fallbackContainer.isVisible = false
        }
        binding.btnRetry.setOnClickListener {
            getProducts()
        }
        productsViewModel.productsError.observe(viewLifecycleOwner) {
            binding.fallbackContainer.isVisible = true
            binding.loading.isVisible = false
            binding.productsContainer.isVisible = false
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            binding.loading.indeterminateDrawable.colorFilter =
                @Suppress("DEPRECATION")
                BlendModeColorFilter(
                    this.resources.getColor(R.color.colorPrimary),
                    BlendMode.SRC_ATOP
                )
        } else {
            @Suppress("DEPRECATION")
            binding.loading.indeterminateDrawable.setColorFilter(
                this.resources.getColor(R.color.colorPrimary),
                PorterDuff.Mode.SRC_ATOP
            )
        }

        return binding.root
    }

    private fun getProducts() {
        binding.productsContainer.isVisible = false
        binding.fallbackContainer.isVisible = false
        binding.loading.isVisible = true
        productsViewModel.getProducts()
    }

    private fun initRecyclerViewProduct(list: ArrayList<Product>) {
        list.sortBy { it.namePlant }
        val manager = LinearLayoutManager(context)
        binding.recyclerCannabis.layoutManager = manager
        binding.recyclerCannabis.adapter =
            ProductsAdapter(list) { product -> onItemSelect(product) }
    }

    private fun onItemSelect(product: Product) {
        findNavController().navigate(
            ProductsFragmentDirections.actionProductsFragmentToDescriptionFragment(
                product.id
            )
        )
    }

    override fun onStart() {
        super.onStart()
        if (Firebase.auth.currentUser == null) {
            findNavController().navigate(R.id.action_productsFragment_to_loginFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        getProducts()
    }
}




