package com.jadevelopers.eden.features.shoppingcar

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.jadevelopers.eden.R.id.productsFragment
import com.jadevelopers.eden.databinding.ItemShoppingCarBinding
import com.jadevelopers.eden.database.entities.ShoppingCar

class ShoppingCarViewHolder(view: View): RecyclerView.ViewHolder(view){

    private val binding = ItemShoppingCarBinding.bind(view)

    fun render(productShoppingModel: ShoppingCar){
        //binding.tvNamePlant.text = productShoppingModel.namePlant
        //binding.descriptionPrice.text = productShoppingModel.price
        //Glide.with(binding.ivCannabis.context).load(productShoppingModel.photo).into(binding.ivCannabis)
        binding.addProducts.setOnClickListener {
            binding.addProducts.findNavController().navigate(productsFragment)
        }
    }
}