package com.jadevelopers.eden.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
        //TODO: cambiar al archivo de strings.xml
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.titulo_productos)
        productsViewModel.productsList.observe(viewLifecycleOwner) {
            initRecyclerView(it)
            binding.loading.isVisible = false
            binding.productsContainer.isVisible = true
        }
        getProducts()
        binding.btnRetry.setOnClickListener {
            getProducts()
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

    private fun onItemSelect(cannabis: Product) {
        Toast.makeText(context, cannabis.namePlant, Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.descriptionFragment)
    }

    override fun onStart() {
        super.onStart()
        if (Firebase.auth.currentUser == null) {
            findNavController().navigate(R.id.action_productsFragment_to_loginFragment)
        }
    }
}




