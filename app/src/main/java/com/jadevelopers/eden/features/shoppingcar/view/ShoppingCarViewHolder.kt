package com.jadevelopers.eden.features.shoppingcar.view

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jadevelopers.eden.R.id.productsFragment
import com.jadevelopers.eden.databinding.ItemShoppingCarBinding
import com.jadevelopers.eden.model.ShoppingCarItem

class ShoppingCarViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemShoppingCarBinding.bind(view)

    fun render(productShoppingModel: ShoppingCarItem) {
        binding.tvNamePlant.text = productShoppingModel.namePlant
        Glide.with(binding.ivCannabis.context).load(productShoppingModel.photo)
            .into(binding.ivCannabis)
        binding.addProducts.setOnClickListener {
            binding.addProducts.findNavController().navigate(productsFragment)
        }
    }
}