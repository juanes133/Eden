package com.jadevelopers.eden.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.jadevelopers.eden.R
import com.jadevelopers.eden.databinding.FragmentShoppingBinding

class ShoppingFragment() : Fragment() {

    private lateinit var binding: FragmentShoppingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentShoppingBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.compra)
        binding.addPoducts.setOnClickListener {
            findNavController().navigate(R.id.productsFragment)
        }
        return binding.root
    }
}