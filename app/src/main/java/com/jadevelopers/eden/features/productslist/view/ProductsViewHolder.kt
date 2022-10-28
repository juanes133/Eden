package com.jadevelopers.eden.features.productslist.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jadevelopers.eden.databinding.ItemProductsBinding
import com.jadevelopers.eden.model.Product

class ProductsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemProductsBinding.bind(view)

    fun render(productModel: Product, onClickListener: (Product) -> Unit) {
        binding.tvNamePlant.text = productModel.namePlant
        binding.descriptionPrice.text = productModel.price
        Glide.with(binding.ivCannabis.context).load(productModel.photo).into(binding.ivCannabis)
        itemView.setOnClickListener {
            onClickListener(productModel)
        }
    }
}