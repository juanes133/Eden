package com.jadevelopers.eden.adapter

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jadevelopers.eden.R.id.productsFragment
import com.jadevelopers.eden.databinding.ItemProductsShoppingBinding
import com.jadevelopers.eden.model.ProductsShopping

class ProductsShoppingViewHolder(view: View): RecyclerView.ViewHolder(view){

    private val binding = ItemProductsShoppingBinding.bind(view)

    fun render(productShoppingModel: ProductsShopping){
        binding.tvNamePlant.text = productShoppingModel.namePlant
        binding.descriptionPrice.text = productShoppingModel.price
        Glide.with(binding.ivCannabis.context).load(productShoppingModel.photo).into(binding.ivCannabis)
        binding.addProducts.setOnClickListener {
            binding.addProducts.findNavController().navigate(productsFragment)
        }
    }
}