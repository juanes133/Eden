package com.jadevelopers.eden.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.jadevelopers.eden.Cannabis
import com.jadevelopers.eden.R
import com.jadevelopers.eden.adapter.CannabisAdapter
import com.jadevelopers.eden.databinding.FragmentProductsBinding

class ProductsFragment : Fragment() {

    private lateinit var binding: FragmentProductsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentProductsBinding.inflate(inflater, container, false)
        binding.btnRetry.setOnClickListener {
            dataBase()
        }
        dataBase()
        (activity as AppCompatActivity).supportActionBar?.title = "Productos"
        return binding.root
    }

    private fun dataBase() {
        binding.fallbackContainer.isVisible = false
        binding.loading.isVisible = true
        val db = Firebase.firestore
        db.collection("products")
            .get()
            .addOnSuccessListener { result ->
                val list = ArrayList<Cannabis>()
                for (document in result) {
                    list.add(
                        Cannabis(
                            document.id,
                            document.data["descripcion"].toString(),
                            document.data["nombrePlanta"].toString(),
                            document.data["sabor"].toString(),
                            document.data["efecto"].toString(),
                            document.data["thc"].toString(),
                            document.data["precio"].toString(),
                            document.data["imagen"].toString()

                        )
                    )
                }
                initRecyclerView(list)
                binding.loading.isVisible = false
                binding.productsContainer.isVisible = true
            }
            .addOnFailureListener {
                binding.loading.isVisible = false
                binding.fallbackContainer.isVisible = true
                binding.productsContainer.isVisible = false
            }
    }

    private fun initRecyclerView(list: ArrayList<Cannabis>) {
        val manager = LinearLayoutManager(context)
        val decoration = DividerItemDecoration(context, manager.orientation)
        binding.recyclerCannabis.layoutManager = manager
        binding.recyclerCannabis.adapter =
            CannabisAdapter(list) { cannabis -> onItemSelect(cannabis) }
        binding.recyclerCannabis.addItemDecoration(decoration)
    }

    private fun onItemSelect(cannabis: Cannabis) {
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




